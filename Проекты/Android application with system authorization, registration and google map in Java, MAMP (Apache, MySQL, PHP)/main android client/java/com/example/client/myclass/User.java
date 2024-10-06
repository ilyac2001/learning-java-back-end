package com.example.client.myclass;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class User extends Guest implements ILoginUser{
    private String phone_number;
    private String password;
    ILoginUser iLoginUser;

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    @Override
    public Meeting createMeeting(String name, LatLng location){
        Meeting meeting = new Meeting(name, location);
        return meeting;
    }

    @Override
    public void ChangeMeeting() {

    }

    @Override
    public void ShowMeeting() {

    }
}
