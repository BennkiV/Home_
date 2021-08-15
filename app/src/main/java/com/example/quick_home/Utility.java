package com.example.quick_home;
import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Utility {
    URL url;
    URLConnection urlConnection;


    public void open_connection(String _url) throws IOException {
        url = new URL(_url);
        urlConnection = (HttpURLConnection) url.openConnection();
    }

    public void close_connection(){

    }
}
