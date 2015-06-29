package com.example.administrator.createba.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 网络图片加载类 单例模式
 * Created by C.jiuxu on 2015/6/10.
 */
public class ImageLoaderTools {
    private static ImageLoaderTools imageLoaderTools;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
//          .showImageOnLoading(R.drawable.loading) //设置图片在下载期间显示的图片
//          .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
//          .showImageOnFail(R.drawable.error)  //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
            .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
//          .decodingOptions(BitmapFactory.Options decodingOptions)//设置图片的解码配置
            .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
//          .preProcessor(BitmapProcessor preProcessor)  设置图片加入缓存前，对bitmap进行设置
            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//          .displayer(new RoundedBitmapDisplayer(20))//不推荐用！！！！是否设置为圆角，弧度为多少 会过多暂用内存
            .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间，可能会出现闪动
            .build();//构建完成

    private ImageLoaderTools() {
    }

    /**
     * 单例模式
     */
    public static ImageLoaderTools getImageLoader() {
        if (imageLoaderTools == null) {
            imageLoaderTools = new ImageLoaderTools();
        }
        return imageLoaderTools;
    }

    /**
     * 加载图片
     *
     * @param url       图片的网络地址
     * @param imageview 要加载图片的容器
     */
    private void imageLoader(String url, ImageView imageview) {
        ImageLoader.getInstance().displayImage(url, imageview, options);
    }

    /**
     * 自适应图片加载
     *
     * @param url       图片的网络地址
     * @param imageview 要加载图片的容器
     * @param mPtions   加载图片是的参数设置
     */
    private void imageLoaderOptions(String url, ImageView imageview, DisplayImageOptions mPtions) {
        ImageLoader.getInstance().displayImage(url, imageview, mPtions);
    }

    /**
     * 图片加载 图片处理
     *
     * @param url       图片的网络地址
     * @param imageview 要加载图片的容器
     * @param listener  加载完成后的图片监听
     */
    private void imageLoaderlistener(String url, ImageView imageview, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(url, imageview, options, listener);
    }
}
