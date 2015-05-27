package com.example.administrator.createba.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.createba.R;
import com.example.administrator.createba.account.AccountTools;
import com.example.administrator.createba.appbasis.BasisActivity;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;


import github.chenupt.dragtoplayout.DragTopLayout;


public class MainActivity extends BasisActivity {

    @Override
    public int setview() {
        return R.layout.activity_main;
    }

    @Override
    public int setonCreateOptionsMenu() {
        return R.menu.menu_main;
    }

    @Override
    public Toolbar settoolbar() {
        return null;
    }

    @Override
    public void dataTransferManage(Intent intent) {

    }

    @Override
    public void initial() {
        DragTopLayout dragLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        View bt = findViewById(R.id.activty_main_tv1);
        bt.setOnClickListener(this);
        dragLayout.setOverDrag(false);
    }

    @Override
    public void logic() {
        isLogin();
    }

    @Override
    public void setonOptionsItemSelected(int id) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activty_main_tv1:
                String device_token = UmengRegistrar.getRegistrationId(this);
                Log.e("有没有啊=", device_token);
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                AccountTools.removeUser(this);

        }

    }

    @Override
    public void callbacks(Bundle bundle) {

    }
}
