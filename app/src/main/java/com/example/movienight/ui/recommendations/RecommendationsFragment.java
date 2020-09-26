package com.example.movienight.ui.recommendations;

import android.graphics.Movie;
import android.os.Bundle;
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
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movienight.MovieObject;
import com.example.movienight.R;

import java.util.ArrayList;

public class RecommendationsFragment extends Fragment {

    private RecommendationsViewModel recommendationsViewModel;
    private ArrayList<MovieObject> movies;
    ViewPager2 viewPager;
    MovieCollectionAdapter movieCollectionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movies = new ArrayList<>();

        MovieObject movie = new MovieObject();
        movie.setPosterPath("http://image.tmdb.org/t/p/w185//6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg");
        movie.setMovieID("694919");
        movie.setGenreID("28");
        movie.setMoviePopularity(2261.976);
        movie.setMovieTitle("Money Plane");

        MovieObject movie2 = new MovieObject();
        movie2.setPosterPath("http://image.tmdb.org/t/p/w185//aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg");
        movie2.setMovieID("337401");
        movie2.setGenreID("28, 12, 18, 14");
        movie2.setMoviePopularity(1458.342);
        movie2.setMovieTitle("Mulan");

        movies.add(movie);
        movies.add(movie2);



        recommendationsViewModel =
                ViewModelProviders.of(this).get(RecommendationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recommendations, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        movieCollectionAdapter = new MovieCollectionAdapter(this, movies);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(movieCollectionAdapter);
    }
}