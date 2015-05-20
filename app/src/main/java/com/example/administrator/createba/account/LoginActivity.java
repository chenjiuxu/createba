package com.example.administrator.createba.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.createba.R;
import com.example.administrator.createba.appbasis.BasisActivity;


public class LoginActivity extends BasisActivity {


    private ImageView login_icon;
    private EditText login_name;
    private EditText user_password;
    private View login_bt;
    private View user_sign_in;
    private View forget_password;

    @Override
    public int setview() {
        return R.layout.activity_login;
    }

    @Override
    public int setonCreateOptionsMenu() {
        return R.menu.menu_login;
    }

    @Override
    public void initial() {
        login_icon = (ImageView) findViewById(R.id.activity_login_icon);
        login_name = (EditText) findViewById(R.id.activity_login_user_name);
        user_password = (EditText) findViewById(R.id.activity_login_user_password);
        login_bt = findViewById(R.id.activity_login_bt);
        user_sign_in = findViewById(R.id.activity_login_user_sign_in);
        forget_password = findViewById(R.id.activity_login_forget_password);

    }

    @Override
    public void logic() {
    }

    @Override
    public void setonOptionsItemSelected(int id) {
    }

    @Override
    public Toolbar settoolbar() {
        return null;
    }

    @Override
    public void dataTransferManage(Intent intent) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void setToolbar() {
    }

    @Override
    public void callbacks(Bundle bundle) {

    }
}
