package cp.fr.listopendata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private TextView salleView;
    private TextView adresseView;
    private TextView codeView;
    private TextView villeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        this.salleView = findViewById(R.id.nameSalle);
        this.adresseView =findViewById(R.id.adresse);
        this.codeView = findViewById(R.id.codepostal);
        this.villeView = findViewById(R.id.ville);

        Bundle extras = getIntent().getExtras();

        salleView.setText(extras.getString("Nomsalle"));
        adresseView.setText(extras.getString("Adresse"));
        codeView.setText(extras.getString("CodePostal"));
        villeView.setText(extras.getString("Ville"));


       }
}
