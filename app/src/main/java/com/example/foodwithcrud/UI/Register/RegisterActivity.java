package com.example.foodwithcrud.UI.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.foodwithcrud.Model.Login.LoginData;
import com.example.foodwithcrud.R;
import com.example.foodwithcrud.UI.Login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {


    @BindView(R.id.edtuser_name)
    EditText edtuserName;
    @BindView(R.id.edtaddress)
    EditText edtaddress;
    @BindView(R.id.edtphonenum)
    EditText edtphonenum;
    @BindView(R.id.radioMale)
    RadioButton radioMale;
    @BindView(R.id.radioFemale)
    RadioButton radioFemale;
    @BindView(R.id.edtusername)
    EditText edtusername;
    @BindView(R.id.edtpassword)
    TextInputEditText edtpassword;
    @BindView(R.id.edtpasswordconfirm)
    TextInputEditText edtpasswordconfirm;
    @BindView(R.id.radioAdmin)
    RadioButton radioAdmin;
    @BindView(R.id.radioUser)
    RadioButton radioUser;
    @BindView(R.id.btnregister)
    Button btnregister;
    private RegisterPresenter mregisterPresenter = new RegisterPresenter(this);
    private ProgressDialog progressDialog;
    private String gender, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setRadio();

    }

    private void setRadio() {
        if (radioAdmin.isChecked()) {
            level = "1";
        } else {
            level = "0";
        }

        if (radioMale.isChecked()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
    }



    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.radioMale, R.id.radioFemale, R.id.radioAdmin, R.id.radioUser, R.id.btnregister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioMale:
                gender = "Male";
                break;
            case R.id.radioFemale:
                gender = "Female";
                break;
            case R.id.radioAdmin:
                level = "1";
                break;
            case R.id.radioUser:
                level = "0";
                break;
            case R.id.btnregister:
                LoginData mloginData = new LoginData();
                mloginData.setUser_name(edtuserName.getText().toString());
                mloginData.setAddress(edtaddress.getText().toString());
                mloginData.setGender(gender);
                mloginData.setPassword(edtpassword.getText().toString());
                mloginData.setPhone_num(edtphonenum.getText().toString());
                mloginData.setUsername(edtusername.getText().toString());
                mloginData.setLevel(level);

                mregisterPresenter.doRegisterUser(mloginData);

                break;

        }
    }


}
