package com.example.movienight.ui.preferences;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movienight.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GenreService extends AsyncTask<String, Integer, String> {

    static final String BASE_URL = "https://api.themoviedb.org/3/genre/movie";
    private final String apiKeyFileName = "myApiKeyFile";
    private List<Genre> genres;
    private Context context;
    private Spinner spinner;


    public GenreService(Context context){
        this.context = context;


    }
    public void setControl(View v){
        this.spinner = (Spinner)v;

    }
    @Override
    protected String doInBackground(String... params) {
        String genres = getGenre();
        return genres;


    }

    @Override
    protected void onPostExecute(String result) {
        Gson gson = new Gson();
        GenreList genreList = gson.fromJson(result, GenreList.class);
        List<Genre> genresTemp = genreList.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, genresTemp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getApiKey() {
        return readAPIFileOnInternalStorage(context, apiKeyFileName);
    }

    public void setApiKey(String apiKey) {
        writeApiKeyOnInternalStorage(context, apiKeyFileName, apiKey);
    }

    public String getGenre() {

        String apiKey = readAPIFileOnInternalStorage(context, apiKeyFileName); //

        if(apiKey.equals("")) {
            return "{}";
        }
        StringBuffer genreString = new StringBuffer();

        try {

            URL url = new URL(BASE_URL + "/list?api_key=" + apiKey + "&language=en-US");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                genreString.append(readLine);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }


        apiKey = "Overwrite";
        return genreString.toString();


    }

    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
        File dir = new File(mcoContext.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File file = new File(mcoContext.getFilesDir()+"/mydir", sFileName);
            if(file.length() > 0) {
                BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
                output.append(sBody);
                output.newLine();
                output.flush();
                output.close();
            }
            else {
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.append(sBody);
                output.newLine();
                output.flush();
                output.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeApiKeyOnInternalStorage(Context mcoContext, String sFileName, String sBody){
        File dir = new File(mcoContext.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File file = new File(mcoContext.getFilesDir()+"/mydir", sFileName);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.append(sBody);
            output.newLine();
            output.flush();
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeFileFromList(Context context, String sFileName, Map<Integer, String> genres){
        try {
            Path p = Paths.get(context.getFilesDir().toString(), "mydir", sFileName);
            sFileName = p.toString();
            File currentFile = new File(sFileName);
            BufferedWriter output = new BufferedWriter(new FileWriter(currentFile));
            for (Map.Entry<Integer, String> entry: genres.entrySet()) {
                String tempString = entry.getValue() + "|" + entry.getKey();
                output.append(tempString);
                output.newLine();
            }
            output.flush();
            output.close();
        }
        catch(Exception e){
            Log.i("Error", "No Stores Preferences: " + e);
        }
    }

    public Map<Integer, String> readFileOnInternalStorage(Context context, String sFileName){
        BufferedReader br = null;
        Map<Integer, String> storedGenres = new HashMap<>();
        try
        {
            Path p = Paths.get(context.getFilesDir().toString(), "mydir",sFileName);
            sFileName = p.toString();
            br = new BufferedReader(new FileReader(sFileName));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                String[] genreArray = line.split("\\|");
                Genre g = new Genre(Integer.parseInt(genreArray[1]), genreArray[0]);
                storedGenres.put(g.getId(), g.getName());
            }
            br.close();
        }
        catch (Exception e)
        {
            Log.i("Error", "Storage File Not Found: " + e);
        }
        return storedGenres;
    }

    public String readAPIFileOnInternalStorage(Context context, String sFileName){
        StringBuilder text = new StringBuilder();
        BufferedReader br = null;
        try
        {
            Path p = Paths.get(context.getFilesDir().toString(), "mydir",sFileName);
            sFileName = p.toString();
            br = new BufferedReader(new FileReader(sFileName));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                text.append(line);

            }

            br.close();
        }
        catch (Exception e)
        {
            Log.i("Error", "Storage File Not Found: " + e);
        }
        // Remove quotations from string
        String s  = text.substring(1, text.length() - 1);
        return s;

    }

    public void removeGenreFromFile(Context mcoContext, String sFileName, String sBody){

        File dir = new File(mcoContext.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }
        Map<Integer, String>  genreMap = readFileOnInternalStorage(mcoContext, sFileName);

        String[] genreArray = sBody.split("\\|");
        Genre g = new Genre(Integer.parseInt(genreArray[1]), genreArray[0]);
        genreMap.remove(g.getId());

        writeFileFromList(mcoContext, sFileName, genreMap);

    }

    public void populateView(Context context, String sFileName, TextView textView){
        Map<Integer, String> contents = readFileOnInternalStorage(context, sFileName);
        StringBuffer display = new StringBuffer();
        for (Map.Entry<Integer, String> entry: contents.entrySet()) {
            display.append(entry.getValue() + " \n");
        }
        if(contents.isEmpty()){
            textView.setText("No Preferences Selected");
        }
        else {
            textView.setText(display.toString());
        }
    }


}
