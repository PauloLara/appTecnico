package com.example.paulo.apptecnico;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Graficos extends Activity {
    WebView myWebView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        myWebView = findViewById(R.id.webview);
        myWebView.loadUrl("http://192.168.15.17/webview.php");
        WebSettings webSettings = myWebView.getSettings();
        //myWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        //Habilitando o JavaScript
        webSettings.setJavaScriptEnabled(true);
    }

}
