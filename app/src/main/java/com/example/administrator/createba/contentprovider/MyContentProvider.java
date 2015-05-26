package com.example.administrator.createba.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.administrator.createba.db.MyDBHelper;

/**
 * ContentProvider用于操作数据库
 * Created by C.jiuxu on 2015/5/26.
 */
public class MyContentProvider extends ContentProvider {
    private MyDBHelper dbHelper;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.administrator.createba.contentprovider.MyContentProvider", "test", 1);//包名  制定以
        uriMatcher.addURI("com.example.administrator.createba.contentprovider.MyContentProvider", "test/#", 2);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {//查询数据库
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 1:
                database.query("test", null, null, null, null, null, null);
                break;
            case 2:
                break;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {//？？？？？？？？？
        String type;
        int tag = uriMatcher.match(uri);
        switch (tag) {
            case 1:
                type = ContentResolver.CURSOR_DIR_BASE_TYPE + "/test";
                return type;
            case 2:
                type = ContentResolver.CURSOR_DIR_BASE_TYPE + "/test.chen";
                return type;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {//增加数据
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 1:
                break;
            case 2:
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);//通知数据发生更新
        return null;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {//批量插入数据库

        getContext().getContentResolver().notifyChange(uri, null);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {//删除
        switch (uriMatcher.match(uri)) {
            case 1:
                break;
            case 2:
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {//更新
        switch (uriMatcher.match(uri)) {
            case 1:
                break;
            case 2:
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }
}
