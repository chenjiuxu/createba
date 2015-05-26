package com.example.administrator.createba.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.createba.R;
import com.example.administrator.createba.appbasis.BasisActivity;
import com.example.administrator.createba.appbasis.Constant;

/**
 * 登陆界面 添加系统账户 完成业务登陆逻辑
 * Created C.jiuxu on 2015/5/20.
 */
public class LoginActivity extends BasisActivity {


    private ImageView login_icon;
    private EditText login_name;
    private EditText user_password;
    private View login_bt;
    private View user_sign_in;
    private View forget_password;
    private AccountManager manager;
    private String tokenname;
    private String tokentype;

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

        login_bt.setOnClickListener(this);

        manager = AccountManager.get(this);
        Intent intent1 = getIntent();
        tokenname = intent1.getStringExtra(Constant.ConstantAccount.TOKIN_ACTIVITY_NAME);
        tokentype = intent1.getStringExtra(Constant.ConstantAccount.TOKEN_ACTIVITY_TYPE);

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

        switch (view.getId()) {
            case R.id.activity_login_bt:
                setToken("陈玖旭", "成功！！！！");
        }
    }

    @Override
    public void setToolbar() {
    }

    @Override
    public void callbacks(Bundle bundle) {

    }

    /**
     * 增加账户 设置系统账户保存token值
     */
    private void setToken(String name, String password) {

        Account account = new Account(name, Constant.ConstantAccount.ACCOUNT_TYPE);//用户name ACCOUNT类型
        if (TextUtils.isEmpty(tokenname)) {
            manager.addAccountExplicitly(account, null, null);//向系统添加账户
            manager.setAuthToken(account, Constant.ConstantAccount.TOKEN_TYPE, password);//向系统添加token

            //设置自动同步(目前关闭)
            // 通知系统,此账户支持同步
            //ContentResolver.setIsSyncable(account, FeedContract.CONTENT_AUTHORITY, 1);
            // 通知系统,这个帐户可以获得汽车网络的同步
            //ContentResolver.setSyncAutomatically(account, FeedContract.CONTENT_AUTHORITY, true);
            // 　推荐一个时间表自动同步。系统可能会修改这个基础　
            // 　其他计划同步和网络利用率。
            //ContentResolver.addPeriodicSync(account, FeedContract.CONTENT_AUTHORITY,new Bundle(), SYNC_FREQUENCY);

        } else {
            manager.setPassword(account, null);
        }
        /////////////可以没有不知道为什么
        Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, name);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constant.ConstantAccount.ACCOUNT_TYPE);
        if (tokentype != null && Constant.ConstantAccount.ACCOUNT_TYPE.equals(tokenname)) {//
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, password);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

}
