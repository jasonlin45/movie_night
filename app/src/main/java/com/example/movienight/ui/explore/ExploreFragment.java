package com.example.movienight.ui.explore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.movienight.R;
import com.example.movienight.ui.webview.WebviewFragment;

public class ExploreFragment extends Fragment {

    private ExploreViewModel exploreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exploreViewModel =
                ViewModelProviders.of(this).get(ExploreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_explore, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        exploreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment webview = new WebviewFragment("https://www.imdb.com/title/tt8374436");
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.webview_container, webview).commit();
    }
}