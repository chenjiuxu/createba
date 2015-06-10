package com.example.administrator.createba.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * ����ͼƬ������ ����ģʽ
 * Created by C.jiuxu on 2015/6/10.
 */
public class ImageLoaderTools {
    private static ImageLoaderTools imageLoaderTools;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
//          .showImageOnLoading(R.drawable.loading) //����ͼƬ�������ڼ���ʾ��ͼƬ
//          .showImageForEmptyUri(R.drawable.ic_launcher)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
//          .showImageOnFail(R.drawable.error)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
            .cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���
            .cacheOnDisk(true)//�������ص�ͼƬ�Ƿ񻺴���SD����
            .considerExifParams(true)  //�Ƿ���JPEGͼ��EXIF��������ת����ת��
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//����ͼƬ����εı��뷽ʽ��ʾ
            .bitmapConfig(Bitmap.Config.RGB_565)//����ͼƬ�Ľ�������
//          .decodingOptions(BitmapFactory.Options decodingOptions)//����ͼƬ�Ľ�������
            .delayBeforeLoading(0)//int delayInMillisΪ�����õ�����ǰ���ӳ�ʱ��
//          .preProcessor(BitmapProcessor preProcessor)  ����ͼƬ���뻺��ǰ����bitmap��������
            .resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ
//          .displayer(new RoundedBitmapDisplayer(20))//���Ƽ��ã��������Ƿ�����ΪԲ�ǣ�����Ϊ���� ����������ڴ�
            .displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ�䣬���ܻ��������
            .build();//�������

    private ImageLoaderTools() {
    }

    /**
     * ����ģʽ
     */
    public static ImageLoaderTools getImageLoader() {
        if (imageLoaderTools == null) {
            imageLoaderTools = new ImageLoaderTools();
        }
        return imageLoaderTools;
    }

    /**
     * ����ͼƬ
     */
    private void imageLoader(String url, ImageView imageview) {
        ImageLoader.getInstance().displayImage(url, imageview, options);
    }

    /**
     * ����ӦͼƬ����
     */
    private void imageLoaderOptions(String url, ImageView imageview, DisplayImageOptions mPtions) {
        ImageLoader.getInstance().displayImage(url, imageview, mPtions);
    }

    /**
     * ͼƬ���� ͼƬ����
     */
    private void imageLoaderlistener(String url, ImageView imageview, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(url, imageview, options, listener);
    }
}
