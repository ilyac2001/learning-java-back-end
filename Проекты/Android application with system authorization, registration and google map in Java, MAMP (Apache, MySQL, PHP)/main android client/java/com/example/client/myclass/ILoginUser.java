package com.example.client.myclass;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public interface ILoginUser { //реализуют зарегистрированные пользователи
    Meeting createMeeting(String name, LatLng location);
    void ChangeMeeting();
    void ShowMeeting();
}
