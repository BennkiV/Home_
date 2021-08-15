package com.example.quick_home;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Utility {
    URL url;
    URLConnection urlConnection;

    public void set_url(String _url) throws MalformedURLException {
        url = new URL(_url);
    }

    public void open_connection() throws IOException {
        urlConnection = (HttpURLConnection) url.openConnection();
    }
}
