package com.example.client;

import android.location.Location;
import android.location.LocationListener;

import com.example.client.myclass.User;
import com.example.client.myclass.Organization;

public interface IContractView { //методы касающиеся получения данных
    User GetUser() throws Exception;
    Organization GetOrganization() throws  Exception;
    void SendMessage(String message);
}
