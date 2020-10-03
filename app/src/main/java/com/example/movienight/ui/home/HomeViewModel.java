package com.example.movienight.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Movie Night!\n" +
                        "To begin, set your preferences \n" +
                        "using the menu in the upper left");
    }

    public LiveData<String> getText() {
        return mText;
    }
}