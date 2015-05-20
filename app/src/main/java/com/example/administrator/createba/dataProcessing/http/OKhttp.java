package com.example.administrator.createba.dataProcessing.http;

import com.example.administrator.createba.BasisApplication;
import com.example.administrator.createba.appbasis.Constant;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 判断网络环境 网络访问 数据传输的工具类
 * Created by C.jiuxu on 2015/5/20.
 */
public class OKhttp {
    private static OkHttpClient okHttpClient = new OkHttpClient();


    static {
        okHttpClient.setCache(new Cache(BasisApplication.getContext().getExternalCacheDir(), Constant.ConstantOkhttp.MAXSIZE));//数据缓存大小
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);//网络连接超时
        okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);


    }

}
