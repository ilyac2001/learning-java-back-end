package com.example.arifmeticoperation.ui.lab2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class lab2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public lab2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}