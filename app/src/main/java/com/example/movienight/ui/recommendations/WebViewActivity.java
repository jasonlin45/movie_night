package com.example.movienight.ui.recommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.movienight.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView wv = (WebView) findViewById(R.id.pop_webview);
        wv.setWebViewClient(new WebViewClient());
        Bundle bd = getIntent().getExtras();
        String url = bd.getString(Intent.EXTRA_TEXT);
        wv.loadUrl(url);
    }
}