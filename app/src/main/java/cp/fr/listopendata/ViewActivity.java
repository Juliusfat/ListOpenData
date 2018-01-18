package cp.fr.listopendata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView salleView;
    private TextView adresseView;
    private TextView codeView;
    private TextView villeView;

    private double latitude;
    private double longitude;
    private GoogleMap mMap;
    private String nomsalle;


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
        latitude = extras.getDouble("Latitude");
        longitude = extras.getDouble("Longitude");
        nomsalle = extras.getString("Nomsalle");



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude , longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(nomsalle));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
