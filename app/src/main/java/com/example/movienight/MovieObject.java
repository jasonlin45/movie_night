package com.example.movienight;


import android.os.Parcelable;

import org.json.JSONObject;

/*The Movie Object is the object that is stores information
* retrieved from the api in its most basic forms.*/
public class MovieObject extends Object  {

    //Class variables
    private double movieID;
    private double[] genreIDs;
    private boolean adult;
    private String movieTitle;
    private double moviePopularity;
    private String posterPath;

    private JSONObject movieDat;

    //Empty constructor
    public void MovieObject(){
    }

    //placeholder constructor
    public void MovieObject(JSONObject movieData){
        //Turn Json to string
        String unpJason = movieData.toString();

        //movieData.get("");
        //TODO: Parse Jason for private variables
        //this.movieID = 1234; //Get the movie id from the JSON
    }

    //private double movieID;
    //setID
    public void setMovieID(double movID){
        this.movieID = movID;
    }

    //getId
    public double getMovieID(){
        return this.movieID;
    }


    //    private double genreIDs[];
    public void setGenreID(double[] genID){
        this.genreIDs = genID;
    }

    public double[] getGenreIDs(){
        return this.genreIDs;
    }


    //    private boolean adult;
    //set
    public void setAdult(boolean adultRating){
        this.adult = adultRating;
    }

    //get
    public boolean getAdult(){
        return this.adult;
    }


    //    private String movieTitle;
    //set
    public void setMovieTitle(String Title){
        this.movieTitle = Title;
    }

    //get
    public String getMovieTitle(){
        return this.movieTitle;
    }


    //    private double moviePopularity;
    //set
    public void setMoviePopularity(double Score){
        this.moviePopularity = Score;
    }

    //get
    public double getMoviePopularity(){
        return this.moviePopularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
