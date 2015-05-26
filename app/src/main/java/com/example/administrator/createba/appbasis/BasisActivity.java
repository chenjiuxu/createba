package com.example.administrator.createba.appbasis;


import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.administrator.createba.R;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;



/**
 * 所有activity的父类 其之类的布局的布局文件中要有     <include layout="@layout/basis_toobar" />
 * Created C.jiuxu on 2015/5/15.
 */
public abstract class BasisActivity extends ActionBarActivity implements View.OnClickListener ,BasisFragment.FragmentCallbacks{
    private Toolbar toolbar;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setview());
        dataTransferManage(getIntent());
        setToolbar();
        setview();
        initial();
        logic();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int createOptions = setonCreateOptionsMenu();
        if (createOptions != 0) {
            getMenuInflater().inflate(createOptions, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setonOptionsItemSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        dataTransferManage(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//友盟统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//友盟统计
    }

    public void setToolbar() {
        toolbar = settoolbar();
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.tl_custom);
        }
        setSupportActionBar(toolbar);//替代ActionBar
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 检测是否登陆
     */
    public void isLogin() {
        AccountManager.get(this).getAuthTokenByFeatures(Constant.ConstantAccount.ACCOUNT_TYPE,
                Constant.ConstantAccount.TOKEN_TYPE,
                new String[0], this, null, null, new callback(),
                null);
    }

    /**
     * 设置布局文件
     */
    public abstract int setview();

    /**
     * 设置menu
     */
    public abstract int setonCreateOptionsMenu();

    /**
     * 初始化页面控件和数据
     */
    public abstract void initial();

    /**
     * 事物逻辑
     */
    public abstract void logic();

    /**
     * 设置menu点击事件
     */
    public abstract void setonOptionsItemSelected(int id);

    /**
     * 自定义toolbar
     */
    public abstract Toolbar settoolbar();

    /**
     * 要设置 android:launchmode="singleTask"
     * 用于activity之间数据的传递
     * <p>
     * A条B  activity B 向A传数据
     * Intent it = new Intent(B.this, B.class);
     * it.put("",数据)
     * startActivity(it);
     */
    public abstract void dataTransferManage(Intent intent);

    /**
     * 获得token
     */
    private class callback implements AccountManagerCallback<Bundle> {

        @Override
        public void run(AccountManagerFuture<Bundle> future) {
            try {
                Bundle bundle = future.getResult();
                 token = bundle.getString(AccountManager.KEY_AUTHTOKEN);//获得token
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }
            //如果有token 跳转下一个页面
        }
    }
}
