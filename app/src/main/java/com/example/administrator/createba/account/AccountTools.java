package com.example.administrator.createba.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.widget.Toast;
import com.example.administrator.createba.appbasis.Constant;
import java.io.IOException;

/**
 * 账户管理的操作类
 * Created by C.jiuxu on 2015/5/26.
 */
public class AccountTools {
    /**
     * 删除系统托管账户
     */
    public static void removeUser(final Activity context) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                AccountManager accountManager = AccountManager.get(context);//获得account管理对象
                if (accountManager != null) {
                    accountManager.invalidateAuthToken(Constant.ConstantAccount.ACCOUNT_TYPE, null);//移除账户类型
                    Account[] accounts = accountManager.getAccountsByType(Constant.ConstantAccount.ACCOUNT_TYPE);
                    if (accounts.length > 0) {
                        AccountManagerFuture<Boolean> booleanAccountManagerFuture = accountManager.removeAccount(accounts[0], null, null);//移除账户
                        try {
                            final Boolean tag = booleanAccountManagerFuture.getResult();
                            context.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (tag){
                                        Toast.makeText(context, "删除完成", Toast.LENGTH_SHORT).show();//删除完成要做的事情
                                    }else {
                                        Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } catch (OperationCanceledException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

}
