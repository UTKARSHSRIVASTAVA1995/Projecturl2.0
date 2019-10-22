package com.example.projecturl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.Map;

public class Utils {

    static void goToUrl(WebView webView, String url) {
        String address = "https://" + url;
        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(address);

    }

    static void search(WebView webView, String url) {
        String urlToLoad = "https://www.google.com/search?q=" + url;
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(urlToLoad);

    }

    static ArrayList<String> getAllSharedPrefList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences((Constants.MyPreferences), Context.MODE_PRIVATE);
        ArrayList<String> arr = new ArrayList<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {

            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            arr.add(entry.getValue().toString());
        }
        return arr;
    }

}
