package com.example.movienight.ui.recommendations;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.movienight.MovieObject;
import com.example.movienight.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MovieCollectionAdapter extends FragmentStateAdapter {
    private ArrayList<MovieObject> movies;

    public MovieCollectionAdapter(Fragment fragment, ArrayList<MovieObject> movies) {
        super(fragment);
        this.movies = movies;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(MovieFragment.ARG_OBJECT, movies.get(position).getMovieTitle());
        args.putString(MovieFragment.ARG_POSTER, movies.get(position).getPosterPath());
        String u = "https://www.themoviedb.org/movie/" + (movies.get(position).getMovieID());
        args.putString(MovieFragment.MOVIE_URL, u);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
