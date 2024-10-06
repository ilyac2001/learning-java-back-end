package com.example.client.myclass;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Meeting {
    private String name;
    private LatLng location;

    public Meeting(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }
}
