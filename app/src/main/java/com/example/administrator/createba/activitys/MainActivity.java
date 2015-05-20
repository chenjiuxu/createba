package com.example.administrator.createba.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.createba.R;
import com.example.administrator.createba.appbasis.BasisActivity;


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

    }

    @Override
    public void callbacks(Bundle bundle) {

    }
}
