package com.example.quick_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText home_text = (EditText) findViewById(R.id.Home_Text);     // define Button
        final Button search_button = findViewById(R.id.Search_Button);          // define Search_text

        search_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // execute after button pressed
                String home = home_text.getText().toString();       // get text from home_text

                if(home != "Hallo"){
                    home_text.setText("Search...");
                }else{
                    home_text.setText("Not Found");
                }
                //"Du Hurensohn"
                // "No U"
            }
        });
    }
}