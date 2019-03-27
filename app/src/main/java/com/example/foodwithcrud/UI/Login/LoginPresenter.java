package com.example.foodwithcrud.UI.Login;

import android.content.Context;

import com.example.foodwithcrud.Data.Remote.ApiClient;
import com.example.foodwithcrud.Data.Remote.ApiInterface;
import com.example.foodwithcrud.Model.Login.LoginData;
import com.example.foodwithcrud.Model.Login.LoginResponse;
import com.example.foodwithcrud.Utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.presenter {

    private final LoginContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private SessionManager msessionManager;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String username, String password) {

        if (username.isEmpty()){
            view.usernameError("Username Belum Terisi");
            return;
        }

        if (password.isEmpty()){
            view.passwordError("Password Salah");
            return;
        }

        view.showProgress();
        Call<LoginResponse> call = apiInterface.loginuser(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();
                if (response.body() != null){
                    if (response.body().getResult() == 1){
                        if (response.body().getData() != null){
                            LoginData loginData = response.body().getData();
                            String message = response.body().getMessage();
                            view.loginSuccess(message, loginData);
                        }else {
                            view.loginFailure("Data tidak ada");
                        }
                    }else {
                        view.loginFailure(response.body().getMessage());
                    }
                }else {
                    view.loginFailure("Data tidak ada");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.loginFailure(t.getMessage());
            }
        });
    }

    @Override
    public void saveDataUser(Context context, LoginData loginData) {
        msessionManager = new SessionManager(context);
        msessionManager.createSession(loginData);
    }

    @Override
    public void checkLogin(Context context) {
        msessionManager = new SessionManager(context);
        Boolean isLogin = msessionManager.isLogin();
        if (isLogin){
            view.isLogin();
        }
    }


}
