package com.example.movienight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MovieServiceTest {

    @Test
    public void movieGetRequest() throws JSONException {
        MovieService service = new MovieService();
        JSONObject j = service.movieGetRequest("24");
        assert(j instanceof JSONObject);
        assertNotNull(j);
        assertEquals("Kill Bill: Vol. 1", j.getString("title"));
    }

    @Test
    public void searchMovieGetRequest() throws IOException {
        MovieService service = new MovieService();
        JSONObject j = service.searchMovieGetRequest();
        assert(j instanceof JSONObject);
        assertNotNull(j);
        System.out.println(j.toString());
    }
}