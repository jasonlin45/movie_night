package com.example.movienight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MovieServiceTest {

    /*
    @Test
    public void movieGetRequest() throws JSONException {
        MovieService service = new MovieService();
        JSONObject j = service.movieGetRequest("24");
        assert(j instanceof JSONObject);
        assertNotNull(j);
        assertEquals("Kill Bill: Vol. 1", j.getString("title"));
    }
     */

    /*
    @Test
    public void searchMovieGetRequest() throws IOException, JSONException {
        MovieService service = new MovieService();
        JSONObject j = service.searchMovieGetRequest();
        assert(j instanceof JSONObject);
        assertNotNull(j);
        System.out.println(j.toString());
        JSONArray movies = j.getJSONArray("results");
        for (int i = 0; i < movies.length(); ++i) {
            JSONObject rec = movies.getJSONObject(i);
            String title = rec.getString("title");
            System.out.println(title);
            JSONArray genres = rec.getJSONArray("genre_ids");
            System.out.println(genres);
            String poster = rec.getString("poster_path");
            System.out.println(poster+"\n\n");

        }
    }
    */
}