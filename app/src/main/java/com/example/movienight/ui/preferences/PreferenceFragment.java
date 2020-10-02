package com.example.movienight.ui.preferences;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movienight.R;
import com.example.movienight.ui.explore.ExploreViewModel;
import com.example.movienight.ui.recommendations.MovieCollectionAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class PreferenceFragment extends Fragment {

    private PreferencesViewModel preferencesViewModel;
    private Spinner customSpinner;
    private GenreService genreService;
    private View root;
    private String storageFile = "storageFile";
    private String apiKeyFileName = "movieApiKeyFile";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        preferencesViewModel = ViewModelProviders.of(this).get(PreferencesViewModel.class);
        root = inflater.inflate(R.layout.fragment_genre, container, false);

        genreService = new GenreService(root.getContext());


        customSpinner = root.findViewById(R.id.spinner);

        genreService.setControl(customSpinner);
        genreService.execute();

        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Genre genre = (Genre) adapterView.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = root.findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreService.writeFileOnInternalStorage(getContext(), storageFile, getSelectedGenre(root));
                List<Genre> contents = genreService.readFileOnInternalStorage(getContext(), storageFile);
                Log.i("current contents: ", contents.toString());
            }
        });
        return root;
    }
    public String getSelectedGenre(View v){
        Genre genre = (Genre)customSpinner.getSelectedItem();
        String name = genre.getName();
        int id = genre.getId();
        return name + "|" + id;
    }

    private void displayGenreData(Genre genre){
        String name = genre.getName();
        int id = genre.getId();

        String genreData = "Name: " +  name + "\nID: " + id;
        Toast.makeText(getActivity(), genreData, Toast.LENGTH_LONG).show();
    }


}
