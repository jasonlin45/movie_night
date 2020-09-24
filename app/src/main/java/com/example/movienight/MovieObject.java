package com.example.movienight;


import android.os.Parcelable;

import org.json.JSONObject;

/*The Movie Object is the object that is stores information
* retrieved from the api in its most basic forms.*/
public class MovieObject extends Object implements Parcelable {

    //Class variables
    private double movieID;
    private double[] genreIDs;
    private boolean adult;
    private String movieTitle;
    private double moviePopularity;
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
    private void setMovieID(double movID){
        this.movieID = movID;
    }

    //getId
    protected double getMovieID(){
        return this.movieID;
    }


    //    private double genreIDs[];
    private void setGenreID(double[] genID){
        this.genreIDs = genID;
    }

    protected double[] getGenreIDs(){
        return this.genreIDs;
    }


    //    private boolean adult;
    //set
    private void setAdult(boolean adultRating){
        this.adult = adultRating;
    }

    //get
    protected boolean getAdult(){
        return this.adult;
    }


    //    private String movieTitle;
    //set
    private void setMovieTitle(String Title){
        this.movieTitle = Title;
    }

    //get
    protected String getMovieTitle(){
        return this.movieTitle;
    }


    //    private double moviePopularity;
    //set
    private void setMoviePopularity(double Score){
        this.moviePopularity = Score;
    }

    //get
    protected double getMoviePopularity(){
        return this.moviePopularity;
    }
}
