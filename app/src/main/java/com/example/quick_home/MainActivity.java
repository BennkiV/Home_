package com.example.quick_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Location variable    (Global)
    FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    // Assign variables     (Global)
    final EditText home_text = (EditText) findViewById(R.id.Home_Text);     // define Button
    final Button search_button = findViewById(R.id.Search_Button);          // define Search_text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Location     OLD !!!
        //--------------------------------------------------------------------------------------------------
        // LocationManager location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // location_manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //------------------------------------------------------------------------------------------------


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check Location permission
                //-----------------------------------------------------------------------------------------
                if(ActivityCompat.checkSelfPermission(MainActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    // When permission granted
                    getLocation();
                }else{
                    // When permission denied
                    ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
                //-----------------------------------------------------------------------------------------


                // execute after button pressed
                String home = home_text.getText().toString();       // get text from home_text

                if(home != "Hallo"){
                    home_text.setText("Search...");
                }else{
                    home_text.setText("Not Found");
                }
            }
        });
    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // Initialize location
                Location location = task.getResult();
                if(location != null){
                    try {
                        //Initialize geoCoder
                        Geocoder geocoder = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        // Initialize address List
                        List<Address> address = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        // Set Latitude on TextView
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
