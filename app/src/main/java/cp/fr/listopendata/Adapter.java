package cp.fr.listopendata;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;



/**
 * Created by Formation on 11/01/2018.
 */

public class Adapter extends ArrayAdapter {
    private Activity context;
    private List<User> data;
    private int resource;
    private LayoutInflater inflater;


    public Adapter (@NonNull Context context, @NonNull List<User> data) {
        super(context, 0, data);

        // création du constructeur
        this.data = data;
        this.resource = resource;
        this.context = (Activity) context;
        this.inflater = this.context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // instenciation de la vue
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_cell, parent, false);

        //((TextView) view.findViewById(R.id.ListTaCheViewName)).setText("");
        //((CheckBox) view.findViewById(R.id.checkBoxValue)).setChecked(true);

        User tachedata = this.data.get(position);
        // liaison entre les données et la ligne
        TextView tachetextview = view.findViewById((R.id.name));
        tachetextview.setText(tachedata.getName());

        //Référence au bouton supprimer


        return view;

    }
}