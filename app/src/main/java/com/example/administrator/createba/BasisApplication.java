package com.example.administrator.createba;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by C.jiuxu on 2015/5/20.
 */
public class BasisApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        setImageLoader(getContext());
    }

    /**
     * 提供上下文对象的方法
     */
    public static Context getContext() {
        return getContext();
    }

    /**
     * 初始化图片缓存
     */
    public void setImageLoader(Context context) {
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
