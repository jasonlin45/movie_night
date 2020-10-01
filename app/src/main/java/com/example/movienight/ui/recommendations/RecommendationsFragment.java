package com.example.movienight.ui.recommendations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Movie;
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
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movienight.MovieList;
import com.example.movienight.MovieObject;
import com.example.movienight.MovieService;
import com.example.movienight.R;

import java.util.ArrayList;

public class RecommendationsFragment extends Fragment {

    private RecommendationsViewModel recommendationsViewModel;
    private ArrayList<MovieObject> movies = new ArrayList<>();
    ViewPager2 viewPager;
    MovieCollectionAdapter movieCollectionAdapter;

    private Intent intent;


    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            MovieList moviesListObject = intent.getParcelableExtra("movie_list");
            movies = moviesListObject.getMovies();

            Log.v("MOVIE", movies.get(0).getMovieTitle());
            //MovieObject movie = intent.getParcelableExtra("movie_list");
            //Log.v("MOVIE", movie.getMovieTitle());
            build();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().registerReceiver(receiver, new IntentFilter(
                MovieService.MOVIES
        ));
        intent = new Intent(getActivity(), MovieService.class);
        getActivity().startService(intent);

        /**
        movies = new ArrayList<>();

        MovieObject movie = new MovieObject();
        movie.setPosterPath("http://image.tmdb.org/t/p/w185//6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg");
        movie.setMovieID("694919");
        int[] ids1 = new int[]{28};
        movie.setGenreID(ids1);
        movie.setMoviePopularity(2261.976);
        movie.setMovieTitle("Money Plane");

        MovieObject movie2 = new MovieObject();
        movie2.setPosterPath("http://image.tmdb.org/t/p/w185//aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg");
        movie2.setMovieID("337401");
        int[] ids2 = new int[]{28, 12, 18, 14};
        movie2.setGenreID(ids2);
        movie2.setMoviePopularity(1458.342);
        movie2.setMovieTitle("Mulan");


        movies.add(movie);
        movies.add(movie2);**/

        recommendationsViewModel =
                ViewModelProviders.of(this).get(RecommendationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recommendations, container, false);

        return root;
    }

    private void build() {
        movieCollectionAdapter = new MovieCollectionAdapter(this, movies);
        viewPager = this.getView().findViewById(R.id.viewPager);
        viewPager.setAdapter(movieCollectionAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().stopService(intent);
        getActivity().unregisterReceiver(receiver);
    }
}