package com.example.foodwithcrud.UI.Register;

import com.example.foodwithcrud.Model.Login.LoginData;

public interface RegisterContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showError(String message);
        void showSuccess(String message);
    }

    interface Presenter {
        void doRegisterUser(LoginData loginData);
    }
}
