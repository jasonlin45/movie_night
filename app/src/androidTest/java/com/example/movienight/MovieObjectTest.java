package com.example.movienight;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieObjectTest {

    /*
    In order to run these test MovieService.searchMovieGetRequest() must be made public temporarily.
     */
    @Test
    public void getMovieID() throws JSONException {
        MovieService s = new MovieService();
        JSONObject j = s.searchMovieGetRequest();
        System.out.println(j.toString());
        MovieObject movie = new MovieObject(j.getJSONArray("results").getJSONObject(0));
        assert("694919".equals(movie.getMovieID()));
    }

    @Test
    public void getMovieTitle() throws JSONException {
        MovieService s = new MovieService();
        JSONObject j = s.searchMovieGetRequest();
        System.out.println(j.toString());
        MovieObject movie = new MovieObject(j.getJSONArray("results").getJSONObject(0));
        assert("Ava".equals(movie.getMovieTitle()));
    }

    @Test
    public void getPosterPath() throws JSONException {
        MovieService s = new MovieService();
        JSONObject j = s.searchMovieGetRequest();
        System.out.println(j.toString());
        MovieObject movie = new MovieObject(j.getJSONArray("results").getJSONObject(0));
        assert("\\/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg".equals(movie.getPosterPath()));

    }
}