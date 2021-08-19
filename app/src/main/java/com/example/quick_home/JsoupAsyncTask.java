package com.example.quick_home;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
    String para;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Hydrogen").get();
            org.jsoup.select.Elements paragraphs=doc.select(".mw-content-ltr p");
            Element firstParagraph = paragraphs.first();
            para=firstParagraph.text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(Void result) {

    }

    public String return_para(){
        return para;
    }
}
