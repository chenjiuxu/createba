package com.example.administrator.createba.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.administrator.createba.appbasis.Constant;


/**
 * 安卓系统账户管理
 * Created by  C.jiuxu 2015/5/15
 */
public class Myaccount extends AbstractAccountAuthenticator {//抽象的账户认证

    private Context context;

    public Myaccount(Context _context) {
        super(_context);
        this.context = _context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {


        return null;
    }


    /**
     * 在系统账户管理中点击添加账户得到运行
     * 添加一个特定类型的帐号类型
     */
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String accountType, String authTokenType, String[] strings, Bundle options) throws NetworkErrorException {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);//关键客户身份验证响应
        intent.putExtra(Constant.ConstantAccount.ACCOUNT_TYPE, accountType);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);//为系统提供intent对象用于添加账户
        return bundle;
    }

    /**
     * 检查帐号的证书
     */
    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    /**
     * 在登陆时检测token临牌是调用 或地token值时
     * 获得某个帐号的authtoken
     */
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        if (!authTokenType.equals(Constant.ConstantAccount.TOKEN_TYPE)) {//判断token的type不是我们定义的tokenType
            Bundle bundle = new Bundle();
            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "your Token Type no equals");
            return bundle;
        }
        String token = AccountManager.get(context).peekAuthToken(account, Constant.ConstantAccount.TOKEN_TYPE);//获取token值
        if (!TextUtils.isEmpty(token)) {//判断是否获得token值
            Bundle bundle = new Bundle();
            bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, Constant.ConstantAccount.ACCOUNT_TYPE);
            bundle.putString(AccountManager.KEY_AUTHTOKEN, token);
            return bundle;
        }
        //如果以上都没有执行就跳转到登陆界面用户登录获得token值
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constant.ConstantAccount.LOGIN_ACTIVITY_NAME, account.name);
        intent.putExtra(Constant.ConstantAccount.TOKEN_ACTIVITY_TYPE, authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
        Bundle serverBundle = new Bundle();
        serverBundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return serverBundle;
    }

    /**
     * 告诉系统token类型
     */
    @Override
    public String getAuthTokenLabel(String authTokenType) {
        String tokenType = authTokenType.equals(Constant.ConstantAccount.TOKEN_TYPE) ? authTokenType : null;
        return tokenType;
    }

    /**
     * 更新本地帐号的证书
     */
    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
    }
}
