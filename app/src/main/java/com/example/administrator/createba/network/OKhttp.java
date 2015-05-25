package com.example.administrator.createba.network;

import com.example.administrator.createba.BasisApplication;
import com.example.administrator.createba.appbasis.Constant;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
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
        okHttpClient.setCookieHandler(new CookieManager(new MyCookieStore(BasisApplication.getContext()), CookiePolicy.ACCEPT_ALL));//设置cookie缓存
        okHttpClient.setCache(new Cache(BasisApplication.getContext().getExternalCacheDir(), Constant.ConstantOkhttp.MAXSIZE));//设置缓存大小
    }

    /**
     * 网络同步请求
     */
    public static Response syncRequest(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 异步网络请求
     */
    public static void asyncRequest(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }


}
