package com.example.administrator.createba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.createba.appbasis.Constant;

/**
 * SQLite数据库的帮助类 用于创建数据库等
 * Created by C.jiuxu on 2015/5/26.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, Constant.ConstantSqlite.DB_HELPER_NAME, null, Constant.ConstantSqlite.DB_HELPER_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//创建数据库 建表

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//版本发生变化时

    }
}
