package com.example.administrator.createba.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ÍøÂç×´Ì¬ÅÐ¶ÏÀà£¨WIFI 2G 3G....£©
 * Created by C.jiuxu on 2015/5/25.
 */
public class IsNetwork {


    /**
     * ÅÐ¶ÏÊÇ·ñÓÐÍøÂç
     */
    public Boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * @param context
     * @return
     * @author sky
     * »ñÈ¡µ±Ç°µÄÍøÂç×´Ì¬  -1£ºÃ»ÓÐÍøÂç  1£ºWIFIÍøÂç2£ºwapÍøÂç3£ºnetÍøÂç
     */
    public int getAPNType(Context context) {
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                netType = 3;
            } else {
                netType = 2;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;
        }
        return netType;
    }

}
