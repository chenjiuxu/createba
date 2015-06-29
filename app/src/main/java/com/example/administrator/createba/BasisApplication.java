package com.example.administrator.createba;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by C.jiuxu on 2015/5/20.
 */
public class BasisApplication extends Application {
    private static Context content;
    @Override
    public void onCreate() {
        super.onCreate();
        content=getApplicationContext();
        setImageLoader(content);
    }

    /**
     * 提供上下文对象的方法
     * @return 上下文对象
     */
    public static Context getContext() {
        return content;
    }

    /**
     * 初始化图片缓存
     * @param context
     */
    public void setImageLoader(Context context) {

        Log.e("什么问题",context.toString());
        // 图像处理
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb 磁盘缓存大小
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new FIFOLimitedMemoryCache(BIND_ABOVE_CLIENT))//内存缓存策略
                .memoryCacheSize(10 * 1024 * 1024)//内存缓存大小
//                .diskCache(new UnlimitedDiscCache())
                .build();
        ImageLoader.getInstance().init(config);


    }

}
