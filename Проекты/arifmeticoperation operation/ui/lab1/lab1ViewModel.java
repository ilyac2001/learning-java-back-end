package com.example.arifmeticoperation.ui.lab1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class lab1ViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public lab1ViewModel() {
        mText = new MutableLiveData<>();
    }
    public LiveData<String> getText() {
        return mText;
    }
}