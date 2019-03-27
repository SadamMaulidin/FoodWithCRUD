package com.example.foodwithcrud.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.foodwithcrud.Model.Login.LoginData;
import com.example.foodwithcrud.UI.Login.LoginActivity;

public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(Constant.PREF_NAME, 0);
        editor = pref.edit();
    }

    public void createSession(LoginData loginData){
        editor.putBoolean(Constant.KEY_IS_LOGIN, true);
        editor.putString(Constant.KEY_USER_ID, loginData.getId_user());
        editor.putString(Constant.KEY_USER_NAME, loginData.getUser_name());
        editor.putString(Constant.KEY_USER_ADDRESS, loginData.getAddress());
        editor.putString(Constant.KEY_USER_PHONENUM, loginData.getPhone_num());
        editor.putString(Constant.KEY_USER_GENDER, loginData.getGender());
        editor.putString(Constant.KEY_USER_LEVEL, loginData.getLevel());
        editor.putString(Constant.KEY_USER_USERNAME, loginData.getUsername());

        editor.commit();
    }

    public  Boolean isLogin(){
        return pref.getBoolean(Constant.KEY_IS_LOGIN, false);
    }

    public  void logOut(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
