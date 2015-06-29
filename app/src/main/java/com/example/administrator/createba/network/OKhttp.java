package com.example.administrator.createba.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.example.administrator.createba.BasisApplication;
import com.example.administrator.createba.appbasis.Constant;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 网络访问 数据传输类
 * Created by C.jiuxu on 2015/5/20.
 */
public class OKhttp {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static MediaType mediaType = MediaType.parse(Constant.ConstantOkhttp.MEDIA_TYPE_PNG);

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
     * @param request
     * @return 网络同步请求结果
     * @throws IOException
     */
    public static Response syncRequest(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 发起异步请求
     * @param request
     * @param callback
     */
    public static void asyncRequest(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步post 无文件提交
     *
     * @param url 网络访问路径
     * @param map 网络访问参数
     * @return 网络请求结果
     * @throws IOException
     */
    public static Response syncPost(String url, Map<String, String> map) throws IOException {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        Set<String> set = map.keySet();
        for (String str : set) {
            formEncodingBuilder.add(str, map.get(str));
        }
        RequestBody body = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return syncRequest(request);
    }

    /**
     * 异步post 无文件提交
     *
     * @param url        网络访问路径
     * @param map        网络访问参数
     * @param myCallback 网络返回封装类
     */
    public static void asyncPost(String url, Map<String, String> map, MyCallback myCallback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        Set<String> set = map.keySet();
        for (String str : set) {
            formEncodingBuilder.add(str, map.get(str));
        }
        RequestBody body = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        asyncRequest(request, myCallback);
    }

    /**
     * 异步post单图片上传
     *
     * @param url        网络访问路径
     * @param map        网络访问参数
     * @param file       封装图片本地路径
     * @param myCallback 网络返回结果封装类
     */
    public static void asyncPsotFile(String url, Map<String, String> map, String file, MyCallback myCallback) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);
        Set<String> set = map.keySet();
        for (String str : set) {
            multipartBuilder.addFormDataPart(str, map.get(str));
        }
        if (!TextUtils.isEmpty(file)) {
            File f = new File(file);
            if (f.exists()) {
                multipartBuilder.addFormDataPart("", "", RequestBody.create(mediaType, f));//请求参数名 文件名 文件的封装对象 ; 可以提交 byte[]
            }
        }
        RequestBody body = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        asyncRequest(request, myCallback);
    }

    /**
     * 异步post批量上传图片
     *
     * @param url        网络访问路径
     * @param map        网络访问参数
     * @param files      封装图片本地路径
     * @param myCallback 网络返回结果封装类
     */
    public static void asyncPsotFiles(String url, Map<String, String> map, List<String> files, MyCallback myCallback) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);
        Set<String> set = map.keySet();
        for (String str : set) {
            multipartBuilder.addFormDataPart(str, map.get(str));
        }
        for (int i = 0; i < files.size(); i++) {
            File f = new File(files.get(i));
            if (f.exists()) {
                multipartBuilder.addFormDataPart("", "", RequestBody.create(mediaType, f));//请求参数名 文件名 文件的封装对象 ; 可以提交 byte[]
            }
        }
        RequestBody body = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        asyncRequest(request, myCallback);
    }

    /**
     * get请求
     *
     * @param url      网络访问路径
     * @param callback 网络返回结果封装类
     */
    public static void asyncGet(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        asyncRequest(request, callback);
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static byte[] getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;//加这个
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 720, 1080);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        return baos.toByteArray();
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


}
