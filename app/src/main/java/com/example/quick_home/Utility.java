package com.example.quick_home;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Utility {
    // Set the url to the DB website
    String url = "https://www.bahn.de/p/view/index.shtml";
    String text = "Test";

    // Take Information from website --- In work
    public String get_info(){
        //  get the HTML
        // Needs new thread so the UI wont freeze
        Thread downloadThread = new Thread(){
            public void run(){
                try {
                    Document doc = Jsoup.connect(url).get();    // get the HTML of site
                    text = doc.text();                          // Test if getSite works
                    if(text.equalsIgnoreCase("") || text == null)
                        text = " Nop";
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        downloadThread.start();
        return text; // temporary
    }

    // Set values to website ---
    public void set_Info(String location, String destination){
        // Location and destination needs to be transmitted to the DB website(url)

    }




}
