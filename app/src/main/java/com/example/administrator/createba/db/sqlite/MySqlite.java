package com.example.administrator.createba.db.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.administrator.createba.appbasis.Constant;
import com.example.administrator.createba.db.MyContentProvider;

/**
 * 定义各个数据表 包含 创建数据库 删除数据库 增删改查 以及常量等
 * Created by C.jiuxu on 2015/6/10.
 */
public class MySqlite {

    private static final String TEXT_TYPE = " TEXT,";
    private static final String INT_TYPE = " INTEGER,";

    public static String SQL_TABLE_NAME = "MySqlite";//表名
    public static Uri MYSQLITE_URL = Uri.parse(MyContentProvider.CONTENT_PROVIDER + "/" + SQL_TABLE_NAME);//指向这张表的uri
    public static Uri MYSQLITE_URL_MORE = Uri.parse(MyContentProvider.CONTENT_PROVIDER + "/" + SQL_TABLE_NAME + "/#");//指向这张表的uri
    private static MySqlite mySqlite;

    /**
     * 表的列名
     * <p>
     * <p>
     * <p>
     * 没有写啊！
     */


    public static final String CREATE_TABLE = "CREATE TABLE " + SQL_TABLE_NAME + "(" + Constant.ConstantSqlite.SQL_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + "" + TEXT_TYPE +
            "" + " INTEGER" + ");";//建表 对表的设计

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + SQL_TABLE_NAME;//删除表

    private MySqlite() {
    }

    /**
     * 单例模式
     */
    public static MySqlite getMySqlite() {
        if (mySqlite == null) {
            mySqlite = new MySqlite();
        }
        return mySqlite;
    }

    /**
     * 查询数据
     * @param db
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public Cursor query(SQLiteDatabase db, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = db.query(SQL_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }


}