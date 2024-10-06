package com.example.client;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.widget.Toast;

import com.example.client.myclass.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

//работа с сервером, отправляем json файл, вообще тут пишутся запросы к БД, но у меня они будут  на сервере, так что здесь просто отправка и получение, по сути эти методы будут храниться на сервере, + безопасно
//Что происходит внутри этих методов - касается только модели. Презентер будет просто вызывать эти методы и его не должно интересовать, как именно они реализованы.
public class Model {

    private class SendRequest extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String[] params){
            String responseFromServer = null;
            DataOutputStream wr = null;
            BufferedWriter writer = null;
            BufferedReader reader = null;
            try {
                String url = params[0];//url
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");

                wr = new DataOutputStream(con.getOutputStream());
                wr.write(params[1].getBytes());
                writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null){
                    response.append(inputLine);
                }
                responseFromServer = response.toString();
            } catch (Exception e){
                return e.getMessage();
            } finally {
                try {
                    if(writer != null) {
                        writer.close();
                    }
                    if(wr != null) {
                        wr.close();
                    }
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(String message){
            super.onPostExecute(message);
            System.out.println(message);
        }
    }

    public boolean AuthorizationVerification(User user){
        boolean result = true;
        String str = "";
        try {
            str = new SendRequest().execute("http://192.168.172.223/search_user_by_phone_number.php", new Gson().toJson(user)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (str.equals("false")) {result = false;}

        return result;
    }

    public void AddUser(User user) {
        new SendRequest().execute("http://192.168.172.223/addUser.php", new Gson().toJson(user));
    }

    public boolean CheckPassword(User user) {
        boolean result = false;
        String str = "";
        try {
            System.out.println(new Gson().toJson(user));
            str = new SendRequest().execute("http://192.168.172.223/search_user_by_phone_number.php", new Gson().toJson(user)).get(); //отправит мне рузультат
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user_chek = new Gson().fromJson(str, User.class);
        if (user_chek.getPassword().equals(user.getPassword())) {
            result = true;
        }
        return result;
    }
}
