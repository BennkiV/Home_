package com.example.quick_home;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Utility {

    public String get_info() throws IOException {
        // 1st Try
  /*      File input = new File("/tmp/input.html");
        Document doc = Jsoup.parse(input, "UTF-8", "https://de.wikipedia.org/wiki/Wiki");

        Elements contents = doc.select("content");  // select html class
        Element content = contents.select("p").first();
        String text = content.text();      // get text
    */
        // 2nd Try
        Document doc = Jsoup.connect("https://de.wikipedia.org/wiki/Wiki").get();
        Element contentDiv = doc.select("div[id=content]").first();
        String text = contentDiv.toString();

        return text; // temporary
    }
}
