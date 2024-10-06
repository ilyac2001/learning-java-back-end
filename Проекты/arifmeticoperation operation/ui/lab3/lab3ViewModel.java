package com.example.arifmeticoperation.ui.lab3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class lab3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public lab3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}