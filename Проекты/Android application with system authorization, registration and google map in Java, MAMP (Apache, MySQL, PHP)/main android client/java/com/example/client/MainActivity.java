package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.client.myclass.Organization;
import com.example.client.myclass.User;

//Это View (представление). Представление отвечает за отображение данных на экране и за обработку действий пользователя.
//методы класса будут вызываться Presenter
public class MainActivity extends AppCompatActivity implements IContractView {

    private Presenter presenter = new Presenter(this);
    private final User user = new User();


    private EditText user_name, user_phone_number, user_password;
    private Button button_add_user, button_login_user;

    private TextView text_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_authorization();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    public void start_authorization() {

        this.user_name = findViewById(R.id.user_name);
        this.user_phone_number = findViewById(R.id.user_phone_number);
        this.user_password = findViewById(R.id.user_password);
        this.button_add_user = findViewById(R.id.button_add_user);
        this.button_login_user = findViewById(R.id.button_login_user);
        this.text_error = findViewById(R.id.text_error);
        button_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.AddUser();
            }
        });

        button_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.CheckLogin()) {
                   text_error.setText("ДОБРО ПОЖАЛОВАТЬ! ВЫ ПОШЛИ!"); //проверяем доступ к геолокации, получаем местоположение
                }
            }
        });
    }


    public void ShowError(int reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }

    @Override
    public void SendMessage(String message) {
        text_error.setText(message);
    }

    @Override
    public User GetUser() throws Exception {
        if (TextUtils.isEmpty(user_name.getText().toString()) ||
                TextUtils.isEmpty(user_phone_number.getText().toString()) ||
                TextUtils.isEmpty(user_password.getText().toString())) {
            ShowError(R.string.is_empty);
            throw new Exception();
        }
        try {
            Long.parseLong(user_phone_number.getText().toString().trim());
        } catch (NumberFormatException nfe) {
            ShowError(R.string.not_number);
            ShowError(R.string.format_number);
            throw new Exception();
        }
        user.setName(user_name.getText().toString().trim());
        user.setPhone_number(user_phone_number.getText().toString().trim());
        user.setPassword(user_password.getText().toString().trim());
        //эта инициализация тестовая, данные о место положении должны обновляться в БД по кнопке Войти button_login_user ???
        return user;
    }

    @Override
    public Organization GetOrganization() throws Exception {
        return null;
    }

}