package com.example.movienight;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.movienight.ui.recommendations.RecommendationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class MovieService extends Service {

    // API_KEY needs to be protected information
    static final String API_KEY = "e08a7ebfc3e3928778e1ab8784d9304f";


    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // Makes a request to TMDb
        try {
            searchForMovies();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    /*
      Gets information from tMDB about a specific movie by its id
     */
    private JSONObject movieGetRequest(String id){
        String search = "https://api.themoviedb.org/3/movie/" + id +
                "?api_key=" + API_KEY + "&language=en-US";

        try {
            return makeRequest(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
    Takes the movie list json and turns it into a parcelable array
     */

    /*
    Gets a list of movies
    TODO add error handling
    TODO add parameters and customization
    TODO Make private?

     */
    private JSONObject searchMovieGetRequest() {
        String sort = "popularity.desc";
        String adult = "false";
        String search = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY +
                "&language=en-US&" + "sort_by=" + sort +
                "&include_adult=" + adult +
                "&page=1";

        try {
            return makeRequest(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Makes the request stored in the url. Returns the JSON object the request gives
     */
    private JSONObject makeRequest(String search) throws IOException {
        URL url = new URL(search);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

        String readLine = null;

        if (responseCode == HttpURLConnection.HTTP_OK) {


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer data = new StringBuffer();

            while ((readLine = in.readLine()) != null) {

                data.append(readLine);

            } in.close();

            try {
                return new JSONObject(data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /*
    Recieves a request for a list of movies.
    TODO Add parameters
     */
    private void searchForMovies() throws JSONException {
        ArrayList<MovieObject> movies = new ArrayList<MovieObject>();
        JSONObject j = searchMovieGetRequest();
        JSONArray listOfMovies = j.getJSONArray("results");
        for (int i = 0; i < 10; ++i) {
            JSONObject rec = listOfMovies.getJSONObject(i);
            movies.add(new MovieObject(rec));
        }

        // TODO Add which class the intent goes to
        MovieList moviesToDisplay = new MovieList(movies);
        Intent intent = new Intent(getBaseContext(), RecommendationsFragment.class);
        intent.putExtra("movie_list", moviesToDisplay);
        startActivity(intent);

    }

}


