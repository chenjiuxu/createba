package com.example.administrator.createba.db.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.administrator.createba.appbasis.Constant;
import com.example.administrator.createba.db.MyContentProvider;

/**
 * ����������ݱ� ���� �������ݿ� ɾ�����ݿ� ��ɾ�Ĳ� �Լ�������
 * Created by C.jiuxu on 2015/6/10.
 */
public class MySqlite {

    private static final String TEXT_TYPE = " TEXT,";
    private static final String INT_TYPE = " INTEGER,";

    public static String SQL_TABLE_NAME = "MySqlite";//����
    public static Uri MYSQLITE_URL = Uri.parse(MyContentProvider.CONTENT_PROVIDER + "/" + SQL_TABLE_NAME);//ָ�����ű��uri
    public static Uri MYSQLITE_URL_MORE = Uri.parse(MyContentProvider.CONTENT_PROVIDER + "/" + SQL_TABLE_NAME + "/#");//ָ�����ű��uri
    private static MySqlite mySqlite;

    /**
     * �������
     * <p>
     * <p>
     * <p>
     * û��д����
     */


    public static final String CREATE_TABLE = "CREATE TABLE " + SQL_TABLE_NAME + "(" + Constant.ConstantSqlite.SQL_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + "" + TEXT_TYPE +
            "" + " INTEGER" + ");";//���� �Ա�����

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + SQL_TABLE_NAME;//ɾ����

    private MySqlite() {
    }

    /**
     * ����ģʽ
     */
    public static MySqlite getMySqlite() {
        if (mySqlite == null) {
            mySqlite = new MySqlite();
        }
        return mySqlite;
    }

    /**
     * ��ѯ����
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