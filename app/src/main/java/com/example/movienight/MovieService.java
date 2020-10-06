package com.example.movienight;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movienight.ui.preferences.Genre;
import com.example.movienight.ui.preferences.GenreService;
import com.example.movienight.ui.recommendations.RecommendationsFragment;
import com.google.gson.JsonObject;

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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MovieService extends Service {

    public static final String MOVIES = "com.example.movienight.ui.recommendations.receiver";

    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

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
        //Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    /*
      Gets information from tMDB about a specific movie by its id
     */
    private JSONObject movieGetRequest(String id) {
        GenreService gs = new GenreService(getApplicationContext());
        String search = "https://api.themoviedb.org/3/movie/" + id +
                "?api_key=" + gs.getApiKey() + "&language=en-US";

        try {
            DownloadMovieTask download = new DownloadMovieTask();
            return download.execute(search).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
        Intent intent = new Intent(MOVIES);
        intent.putExtra("movie_list", moviesToDisplay);
        sendBroadcast(intent);

    }


    /*
    Gets a list of movies
    TODO add error handling
    TODO add parameters and customization
    TODO Make private?
     */
    public JSONObject searchMovieGetRequest() {
        String sort = "popularity.desc";
        String adult = "false";
        GenreService gs = new GenreService(this.getBaseContext());
        Map<Integer, String> genreMap = gs.readFileOnInternalStorage(getBaseContext(), "storageFile");
        List<Genre> genres = new ArrayList<>();
        for(Map.Entry<Integer, String> entry: genreMap.entrySet()){
            genres.add(new Genre(entry.getKey(),entry.getValue()));
        }

        String search = "https://api.themoviedb.org/3/discover/movie?api_key=" + gs.getApiKey() +
                "&language=en-US&" + "sort_by=" + sort +
                "&include_adult=" + adult +
                "&page=1";
        if(genres.size()>0) {
            search += "&with_genres=" + genres.get(0).getId();
            for(int i = 1; i < genres.size(); i++) {
                search += "," + genres.get(i).getId();
            }
        }
        try {
            DownloadMovieTask download = new DownloadMovieTask();
            return download.execute(search).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    class DownloadMovieTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = params[0];
            JSONObject result;
            try {
                result = makeRequest(url);
                return result;
            } catch (IOException ex) {
                return null;
            }
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

                }
                in.close();

                try {
                    return new JSONObject(data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

    }

}

