package com.example.administrator.createba.network;

/**
 * Okhttp异步网络请求的相应类执行在子线程之中
 * Created by C.jiuxu on 2015/5/25.
 */

import android.app.Activity;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class MyCallback<T> implements Callback {

    private Class aClass;
    private Activity activity;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Object data;

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
    public void onResponse(Response response) throws IOException {//请求成功 运行在工作线程中
        String json = response.body().string();
        if (aClass != null) {
            data = objectMapper.readValues(new JsonFactory().createParser(json), aClass).next();//jackson 解析
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                response((T) data);
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
    public abstract void response(T data);


}
