package com.example.administrator.createba.network;

/**
 * Okhttp�첽�����������Ӧ��ִ�������߳�֮��
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
    public void onFailure(Request request, IOException e) {//��������ʧ��
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                failure();
            }
        });


    }

    @Override
    public void onResponse(Response response) throws IOException {//����ɹ�
        String json = response.body().string();
        if (aClass != null) {
            Gson gson = new Gson();
            gson.fromJson(json, aClass);//�������ݵ�ʵ����
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                response();
            }
        });
    }

    /**
     * ��������ʧ�ܺ�ִ��
     * ���߳�
     */
    public abstract void failure();

    /**
     * ������������
     * ���߳�
     */
    public abstract void response();


}
