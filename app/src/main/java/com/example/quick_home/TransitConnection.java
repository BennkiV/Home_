package com.example.quick_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TransitConnection extends AppCompatActivity {

    // Assign variables
    TextView origin1;
    TextView destination1;
    TextView originTime1;
    TextView destinationTime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit_connection);

        // Initialize Variables
        origin1 = findViewById(R.id.Origin1);
        destination1 = findViewById(R.id.Destination2);
        originTime1 = findViewById(R.id.StartTime1);
        destinationTime1 = findViewById(R.id.DestinationTime1);
    }

    // get the information from website and paste it into View
    // use Utility functions
    protected void inProsses(){

    }


}