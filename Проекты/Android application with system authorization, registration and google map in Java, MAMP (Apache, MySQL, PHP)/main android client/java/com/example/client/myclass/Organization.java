package com.example.client.myclass;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Organization extends Guest implements ILoginUser{

    @Override
    public Meeting createMeeting(String name, LatLng location) {
        return null;
    }

    @Override
    public void ChangeMeeting() {

    }

    @Override
    public void ShowMeeting() {

    }
}
