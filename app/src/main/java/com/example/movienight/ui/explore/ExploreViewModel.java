package com.example.movienight.ui.explore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExploreViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExploreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Explore All Movies");
    }

    public LiveData<String> getText() {
        return mText;
    }
}