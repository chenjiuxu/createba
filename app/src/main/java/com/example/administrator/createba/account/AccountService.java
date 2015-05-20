package com.example.administrator.createba.account;

import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 账户管理服务
 * */
public class AccountService extends Service {
    private Myaccount myaccount;


    public AccountService() {
    }
    @Override
    public IBinder onBind(Intent intent) {//Service 注册到系统。。。。。同步Z！！！！！
        if (intent != null && AccountManager.ACTION_AUTHENTICATOR_INTENT.equals(intent.getAction())) {
            return getAuthenticator().getIBinder();
        }
        return myaccount.getIBinder();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myaccount =new Myaccount(this);
    }
    private Myaccount getAuthenticator() {
        if (myaccount == null) {
            myaccount = new Myaccount(this);
        }
        return myaccount;
    }

}
