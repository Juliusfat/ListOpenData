package cp.fr.listopendata;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private List<User> userList;
    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromHttp();
    }

    private void processResponse(String response) {
        userListView = findViewById(R.id.taCheListView);
        userList = responseToList(response);
        Adapter tacheAdapter = new Adapter(this, userList);
        userListView.setAdapter(tacheAdapter);


        userListView.setOnItemSelectedListener(this);

        /*final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());*/
    }

    private void getDataFromHttp() {
        String url = "https://opendata.lillemetropole.fr/api/records/1.0/search/?dataset=installation-sportives&facet=cominsee&facet=comlib&apikey=6953e7f77733a4906533bf4b9f7b9eabad490d23e85d272d838e4229";

        //Définition de la requête
        StringRequest request = new StringRequest(
                //Méthode de la requête http
                Request.Method.GET,
                url,
                //Gestionnaire de succès
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("HTTP", response);
                        processResponse(response);

                    }
                },
                //Gestionnaire d'erreur
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("HTTP", error.getMessage());

                    }
                }
        );

        //Ajout de la requête à la file d'exécution
        Volley.newRequestQueue(this)
                .add(request);
    }

    //Conversion d'une réponse json(chaine de caractère) en une liste User
    private List<User> responseToList(String response) {
        List<User> list = new ArrayList<>();
        try {
            JSONObject item = new JSONObject(response);
            JSONArray jsonUsers = item.getJSONArray("records");


            for (int i = 0; i < jsonUsers.length(); i++) {
                item = (JSONObject) jsonUsers.get(i);
                User user = new User();

                JSONObject name = item.getJSONObject("fields");
                user.setName(name.getString("insnom"));
                user.setAdress(name.getString("inslibellevoie"));
                user.setCodepostal(name.getString("inscodepostal"));
                user.setVille(name.getString("comlib"));
                JSONArray jsonGEO = name.getJSONArray("geo");
                user.setLat(jsonGEO.getDouble(0));
                user.setLon(jsonGEO.getDouble(1));
                //ajout de l'utilisateur à la liste
                list.add(user);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        User vuser = userList.get(position);

        Intent FormIntent = new Intent(this,ViewActivity.class);
        FormIntent.putExtra("Nomsalle",vuser.getName());
        FormIntent.putExtra("Adresse",vuser.getAdress());
        FormIntent.putExtra("CodePostal",vuser.getCodepostal());
        FormIntent.putExtra("Ville",vuser.getVille());
        FormIntent.putExtra("Latitude",vuser.getLat());
        FormIntent.putExtra("Longitude",vuser.getLon());
        startActivity(FormIntent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

