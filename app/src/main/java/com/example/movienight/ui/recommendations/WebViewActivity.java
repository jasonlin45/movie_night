package com.example.movienight.ui.recommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.movienight.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView wv = (WebView) findViewById(R.id.pop_webview);
        wv.getSettings().setJavaScriptEnabled(false);
        wv.setWebViewClient(new WebViewClient());
        Bundle bd = getIntent().getExtras();
        final String url = bd.getString(Intent.EXTRA_TEXT);
        wv.loadUrl(url);

        Button share = findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey, I just got recommended this movie: " + url + ", check it out!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }
}