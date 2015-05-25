package com.example.administrator.createba.network;

/**
 * Okhttp异步网络请求的相应类执行在子线程之中
 * Created by C.jiuxu on 2015/5/25.
 */

import android.app.Activity;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class MyCallback implements Callback {

    private Class aClass;
    private Activity activity;

    public MyCallback(Activity activity, Class aClass) {
        this.aClass = aClass;
        this.activity = activity;
    }


    @Override
    public void onFailure(Request request, IOException e) {//网络请求失败
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                failure();
            }
        });


    }

    @Override
    public void onResponse(Response response) throws IOException {//请求成功
        String json = response.body().string();
        if (aClass != null) {
            Gson gson = new Gson();
            gson.fromJson(json, aClass);//储存数据到实体类
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                response();
            }
        });
    }

    /**
     * 网络请求失败后执行
     * 主线程
     */
    public abstract void failure();

    /**
     * 网络请求结果、
     * 主线程
     */
    public abstract void response();


}
