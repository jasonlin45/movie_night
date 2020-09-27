package com.example.movienight;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieList implements Parcelable {

    private ArrayList<MovieObject> movies = new ArrayList<>();

    public MovieList(ArrayList<MovieObject> movieList) {
        this.movies = movieList;
    }

    protected MovieList(Parcel in) {

        in.readTypedList(this.movies, MovieObject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeTypedList(this.movies);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel in) {
            return new MovieList(in);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

    public ArrayList<MovieObject> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieObject> movies) {
        this.movies = movies;
    }

}
