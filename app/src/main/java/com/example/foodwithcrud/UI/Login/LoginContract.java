package com.example.foodwithcrud.UI.Login;

import android.content.Context;

import com.example.foodwithcrud.Model.Login.LoginData;

public interface LoginContract {

    interface View{
        void showProgress();
        void hideProgress();
        void loginSuccess(String msg, LoginData loginData);
        void loginFailure(String msg);
        void usernameError(String msg);
        void passwordError(String msg);
        void isLogin();
    }

    interface presenter{
        void doLogin(String username, String password);
        void saveDataUser(Context context, LoginData loginData);
        void checkLogin(Context context);
    }
}
