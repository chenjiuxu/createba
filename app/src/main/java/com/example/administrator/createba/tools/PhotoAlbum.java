package com.example.administrator.createba.tools;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.administrator.createba.BasisApplication;
import com.example.administrator.createba.appbasis.Constant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用于浏览手机相册 保存图片刷新到相册
 * Created by C.jiuxu on 2015/5/25.
 */
public class PhotoAlbum {


    /**
     * 浏览相册（跳转至相册）
     *
     * @param activity
     * @param requestCode 请求码
     */
    public void browsePhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 获得相册被选中图片的路径
     * <p>
     * 在onActivityResult   if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) 通过调用
     * <p>
     *
     * @param data 系统相册返回的对象
     * @return 选中图片的路径
     */
    public String getSelectedPhoto(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = BasisApplication.getContext().getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 传入bitmap 保存到相册
     *
     * @param activity
     * @param bitmap   要保存的对象
     */
    public void saveImageToPhoto(Activity activity, Bitmap bitmap) {
        File sdDir = Environment.getExternalStorageDirectory();
        File path = new File(sdDir + Constant.ConstantTools.PHOTO_SAVE_IMAGE);
        if (!path.exists()) {
            path.mkdirs();
        }
        String time = DateTools.getDateToString("yyyyMMddHHmmssSS");
        File file = new File(path, time + ".png");
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            boolean a = bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);//保存到指定文件夹
            if (a) {
                Toast.makeText(activity, "以保存到相册", Toast.LENGTH_LONG).show();
                MediaScannerConnection.scanFile(activity, new String[]{file.toString()}, null, null);//系统相册刷新
            } else {
                Toast.makeText(activity, "保存失败！", Toast.LENGTH_LONG).show();
            }
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
