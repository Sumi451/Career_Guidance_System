package com.example.careerguidancesystem;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Chatbot extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot);
        WebView webView=findViewById(R.id.chatbot_webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true); // Optimize layout
        webSettings.setUseWideViewPort(true); // Enable viewport support
       // webSettings.setSupportZoom(true); // Enable zoom support
     //   webSettings.setBuiltInZoomControls(true); // Show zoom controls
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webSettings.setSafeBrowsingEnabled(true);
        }
        webView.loadUrl("https://cdn.botpress.cloud/webchat/v2.1/shareable.html?botId=52523406-8063-42a1-aa4d-603b9e5a8de0");
    }
}
