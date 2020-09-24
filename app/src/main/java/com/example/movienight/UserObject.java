package com.example.movienight;

import java.util.ArrayList;


public class UserObject extends Object {

    //Class Variables
    private ArrayList genreValues;

    //public void UserObject(){}
    public void UserObject(){
        //Empty
    }

    //Assignment Methods
    protected void addGenre(double genreID){
        this.genreValues.add(genreID);
    }

    protected void removeGenre(double genreID){
        this.genreValues.remove(genreID);
    }

    public ArrayList getGenreValues(){
        return this.genreValues;
    }
}
