package com.example.foodwithcrud.UI.Register;

import android.util.Log;

import com.example.foodwithcrud.Data.Remote.ApiClient;
import com.example.foodwithcrud.Data.Remote.ApiInterface;
import com.example.foodwithcrud.Model.Login.LoginData;
import com.example.foodwithcrud.Model.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void doRegisterUser(LoginData loginData) {
        if (loginData != null){
            if (!loginData.getUsername().isEmpty() &&
                    !loginData.getAddress().isEmpty() &&
                    !loginData.getPhone_num().isEmpty() &&
                    !loginData.getLevel().isEmpty() &&
                    !loginData.getGender().isEmpty() &&
                    !loginData.getUser_name().isEmpty() &&
                    !loginData.getPassword().isEmpty()){

                view.showProgress();
                Call<LoginResponse> call = apiInterface.registerUser(
                        loginData.getUsername(),
                        loginData.getPassword(),
                        loginData.getUser_name(),
                        loginData.getAddress(),
                        loginData.getGender(),
                        loginData.getPhone_num(),
                        loginData.getLevel()
                );

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        view.hideProgress();
                        if (response.body() != null){
                            if (response.body().getResult() == 1){
                                view.showSuccess(response.body().getMessage());
                            }else {
                                view.showError(response.body().getMessage());
                            }
                        }else {
                            view.showError("Data is empty");
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        view.hideProgress();
                        view.showError(t.getMessage());
                        Log.i("cek", t.getMessage());

                    }
                });
            }else {
                view.showError("Fill in all data");
            }
        }

    }
}
