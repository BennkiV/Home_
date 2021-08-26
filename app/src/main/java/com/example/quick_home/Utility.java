package com.example.quick_home;

import android.text.Editable;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utility {
    // Set the url to the DB website
    String url = "https://www.bahn.de/p/view/index.shtml";
    String text = "Test";
    Document doc;

    // Take Information from website as HTML
    public String get_info(){
        //  get the HTML
        // Needs new thread so the UI wont freeze
        Thread downloadThread = new Thread(){
            public void run(){
                try {
                    Document doc = Jsoup.connect(url).get();    // get the HTML of site
                    //text = doc.title();
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

    // in process, will get the info from connection
    public String get_info(int b /*in process*/ ){
        //  get the HTML
        return text; // temporary
    }

    // Set values to website --- didn't work as plant
    public Document set_Info(String location, Editable destination){
        // Location and destination needs to be transmitted to the DB website(url)
        Thread uploadThread = new Thread() {
            @Override
            public void run() {
                super.run();
                try{
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    byte[] outputInBytes = location.getBytes("UTF-8");
                    OutputStream os = conn.getOutputStream();
                    os.write(outputInBytes);

                    // get response as String
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();
                    conn.disconnect();

                    // parse whit Jsoup
                    doc = Jsoup.parse(response.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        uploadThread.start();
        return doc;

    }




}
