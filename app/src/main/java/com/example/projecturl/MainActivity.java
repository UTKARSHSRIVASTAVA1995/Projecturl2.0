package com.example.projecturl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView1;
    EditText url_Login;
    Button bt1;
    String Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView1 =(WebView)findViewById(R.id.webView1);
        url_Login= (EditText)findViewById(R.id.url_Login);
        bt1 = (Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address = "http://" + url_Login.getText().toString();
                WebSettings webSetting = webView1.getSettings();
                webSetting.setBuiltInZoomControls(true);
                webSetting.setJavaScriptEnabled(true);
                webView1.setWebViewClient(new WebViewClient());
                webView1.loadUrl(Address);
            }
        });
    }

}
