package com.example.foodwithcrud.Data.Remote;

import com.example.foodwithcrud.Model.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    //create endpoit for login
    @FormUrlEncoded
    @POST("loginuser.php")
    Call<LoginResponse> loginuser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registeruser.php")
    Call<LoginResponse> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("user_nama") String user_nama,
            @Field("address") String address,
            @Field("gender") String gender,
            @Field("phone_num") String phone_num,
            @Field("level") String level
    );

}
