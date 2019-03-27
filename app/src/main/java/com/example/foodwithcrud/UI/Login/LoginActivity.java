package com.example.foodwithcrud.UI.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodwithcrud.Model.Login.LoginData;
import com.example.foodwithcrud.R;
import com.example.foodwithcrud.UI.Main.MainActivity;
import com.example.foodwithcrud.UI.Register.RegisterActivity;
import com.example.foodwithcrud.Utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.txt_input_layout)
    TextInputLayout txtInputLayout;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;

    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.txt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.doLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.txt_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(String msg, LoginData loginData) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        LoginData mloginData = new LoginData();
        mloginData.setId_user(loginData.getId_user());
        mloginData.setAddress(loginData.getAddress());
        mloginData.setGender(loginData.getGender());
        mloginData.setLevel(loginData.getLevel());
        mloginData.setUser_name(loginData.getUser_name());
        mloginData.setPhone_num(loginData.getPhone_num());
        mloginData.setPassword(loginData.getPassword());
        mloginData.setUsername(loginData.getUsername());

        startActivity(new Intent(this, MainActivity.class).putExtra(Constant.KEY_LOGIN, mloginData));
        finish();

    }

    @Override
    public void loginFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameError(String msg) {
        edtUsername.setError(msg);
        edtUsername.setFocusable(true);
    }

    @Override
    public void passwordError(String msg) {
        edtPassword.setError(msg);
        edtPassword.setFocusable(true);
    }

    @Override
    public void isLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
