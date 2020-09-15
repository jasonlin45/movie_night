package com.example.movienight.ui.webview;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.movienight.R;

public class WebviewFragment extends Fragment {

    private WebviewViewModel mViewModel;
    private String url;

    public WebviewFragment() {
        super();
    }

    public static WebviewFragment newInstance() {
        return new WebviewFragment();
    }

    public WebviewFragment(String url){
        super();
        this.url = url;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.webview_fragment, container, false);
        WebView wv = v.findViewById(R.id.webview);
        wv.loadUrl(this.url);
        wv.setWebViewClient(new WebViewClient());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WebviewViewModel.class);
        // TODO: Use the ViewModel
    }

}