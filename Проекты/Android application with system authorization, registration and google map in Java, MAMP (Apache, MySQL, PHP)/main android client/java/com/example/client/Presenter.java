package com.example.client;
//вызывает методы из View и Model
//От представления презентер получает данные о том, какие кнопки были нажаты пользователем, и решает, как отреагировать на эти нажатия. Если надо что-то отобразить, то презентер сообщает об этом представлению. А если нужно сохранить/получить данные, он использует модель.

import android.text.TextUtils;

import com.example.client.myclass.Meeting;
import com.example.client.myclass.User;
import com.google.android.gms.maps.model.LatLng;

public class Presenter {
    private Model model = new Model();
    private IContractView view;

    public Presenter(IContractView view) {
        this.view = view;
    }
    public Presenter() { }

    public void AddUser() {
        User user;
        try {
            user = view.GetUser();
            if (model.AuthorizationVerification(user)){
                view.SendMessage("Пользователь с таким номером телефона уже существует");
            } else {
                try {
                    model.AddUser(user);
                    view.SendMessage("Вы зарегистрировались");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean CheckLogin(){
        boolean result = false;
        User user;
        try {
            user = view.GetUser();
            if (!model.AuthorizationVerification(user)){
                view.SendMessage("Пользователя с таким номером телефона не существует");
            }
            if (model.CheckPassword(user)){
                result = true;
            }   else {
                view.SendMessage("Неверный пароль");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
    
}

