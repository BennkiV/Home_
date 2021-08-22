package com.example.quick_home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Location variable    (Global)
    FusedLocationProviderClient fusedLocationProviderClient;
    String current_location;        // string contains the current location after func getLocation

    // WebSite
    Utility utility;

    // Assign variables     (Global)
    EditText home_text;
    Button search_button;
    TextView text_view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        // Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();          // need to be done else the location wont show up

        // Website
        getInternetPermission();
        utility = new Utility();

        // GUI
        home_text =  findViewById(R.id.Home_Text);
        search_button = findViewById(R.id.Search_Button);
        text_view1 = findViewById(R.id.textView);

        // Button Click
        search_button.setOnClickListener(v -> {
            // execute after button pressed
            String home = home_text.getText().toString();       // get text from home_text
            if (!home.equalsIgnoreCase("")) {             // string compare -_-
                // get location
                getLocation();
                text_view1.setText(current_location);   // print location in text_view1
                home_text.setText("Search...");
            } else {
                home_text.setText("Try again");
            }

/*               try {
                home_text.setText(utility.get_info());
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
            // go to next Activity
            // transitConnection();

        });
    }

    // get last known location
    private void getLocation() {
        // If permission abfrage hier, da Java sich beschwert
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            // Initialize location
            Location location = task.getResult();
            if (location != null) {

                try {
                    //Initialize geoCoder
                    Geocoder geocoder = new Geocoder(MainActivity.this,
                            Locale.getDefault());
                    // Initialize address List
                    List<Address> address = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1
                    );
                    current_location = address.get(0).getAddressLine(0);          // set global string to location
                } catch (IOException e) {
                    e.printStackTrace();
                    text_view1.setText("Faile");            // test
                }

            }
        });
    }

    // getInternetPermission
    private void getInternetPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.INTERNET}, 44);

            //return;
        }
    }

    // Go to TransitConnection Activity
    public void transitConnection(){
        Intent i = new Intent(this, TransitConnection.class);
        //  start the activity if location is found and input is "correct" and not null

        startActivity(i);
    }

}
