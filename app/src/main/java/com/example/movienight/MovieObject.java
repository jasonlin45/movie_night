package com.example.movienight;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

/*The Movie Object is the object that is stores information
* retrieved from the api in its most basic forms.*/
public class MovieObject extends Object implements Parcelable {

    //Class variables
    private String movieID;
    private String genreIDs;
    private boolean madult;
    private String movieTitle;
    private double moviePopularity;
    private String posterPath;

    private JSONObject movieDat;

    public MovieObject(){}

    //placeholder constructor
    public MovieObject(JSONObject movieData) throws JSONException {
        //Extract Relevant string values
        this.movieID = movieData.getString("id");
        this.posterPath = movieData.getString("poster_path");
        String popularity = movieData.getString("popularity");
        this.moviePopularity = Double.valueOf(popularity);

        //Get Boolean
        this.madult = false;
        String adult = movieData.getString("adult");
        if (adult == "true"){ this.madult = true;}

        //Set genre ids
        this.genreIDs = movieData.getString("genre_ids");
    }

    //private double movieID;
    //setID
    public void setMovieID(String movID){
        this.movieID = movID;
    }

    //getId
    public String getMovieID(){
        return this.movieID;
    }


    //    private double genreIDs[];
    public void setGenreID(String genID){
        this.genreIDs = genID;
    }

    public String getGenreIDs(){
        return this.genreIDs;
    }


    //    private boolean adult;
    //set
    public void setAdult(boolean adultRating){
        this.madult = adultRating;
    }

    //get
    public boolean getAdult(){
        return this.madult;
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


    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(movieID);
        out.writeString(movieTitle);
        out.writeString(posterPath);
        out.writeString(genreIDs);
        out.writeBoolean(madult);
        out.writeDouble(moviePopularity);

    }


    //Construct from in
    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected MovieObject(Parcel in){
        movieID = in.readString();
        movieTitle = in.readString();
        posterPath = in.readString();
        genreIDs = in.readString();
        madult = in.readBoolean();
        moviePopularity = in.readDouble();

    }
    // Reconstruct class from parcel constructors
    public static final Parcelable.Creator<MovieObject> CREATOR = new Parcelable.Creator<MovieObject>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int i) {
            return new MovieObject[0];
        }


    };
}
