package com.example.quick_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Location variable    (Global)
    FusedLocationProviderClient fusedLocationProviderClient;
    // Assign variables     (Global)
    EditText home_text;     // define Button
    Button search_button;          // define Search_text
    TextView text_view1;
    TextView text_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        home_text = (EditText) findViewById(R.id.Home_Text);
        search_button = findViewById(R.id.Search_Button);
        text_view1 = findViewById(R.id.textView);
        text_view2 = findViewById(R.id.textView2);

        // Location     OLD !!!
        //--------------------------------------------------------------------------------------------------
        // LocationManager location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // location_manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //------------------------------------------------------------------------------------------------

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check Location permission
/*                //-----------------------------------------------------------------------------------------
                if (ActivityCompat.checkSelfPermission(MainActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // When permission granted
                    getLocation();
                } else {
                    // When permission denied
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
  */
                getLocation();
                //-----------------------------------------------------------------------------------------
                // execute after button pressed
                String home = home_text.getText().toString();       // get text from home_text

                if (home != "Hallo") {
                    home_text.setText("Search...");
                } else {
                    home_text.setText("Not Found");
                }
            }
        });
    }


    private void getLocation() {
        // If abfrage hier da Java sich beschwert
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // Initialize location
                Location location = task.getResult();
                if (location != null) {                         // Location ist Null, function wird übersprungen !?

                    try {
                        //Initialize geoCoder
                        Geocoder geocoder = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        // Initialize address List
                        List<Address> address = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        // Set Latitude on TextView
                        text_view1.setText(Html.fromHtml(                                           // ausgabe der coordinaten brete
                                "<font color?'#6200'><b>Latitude :</b><br></font>"
                                        + address.get(0).getLatitude()
                        ));
                        // Set Longitude
                        text_view2.setText(Html.fromHtml(                                           // ausgabe der coordinaten länge
                                "<font color?'#6200'><b>Longitude :</b><br></font>"
                                        + address.get(0).getLongitude()
                        ));

                        text_view1.setText("Doooooo");          // test ob function aufgerufen wird
                    } catch (IOException e) {
                        e.printStackTrace();
                        text_view1.setText("Faile");            // test
                    }

                }
            }
        });
    }
}
