package com.example.administrator.createba.appbasis;


/**
 * 程序中的常量数据
 * Created by C.jiuxu on 2015/5/15.
 */
public class Constant {

    public static final String applicationId = "com.example.administrator.createba";//app包名

    /**
     * 系统账户常量
     */
    public static class ConstantAccount {
        public static final String ACCOUNT_TYPE = "com.example.administrator.createba.basisaccounttype";//账户类型
        public static final String TOKEN_TYPE = applicationId + "tokentype";//token类型
        public static final String TOKIN_ACTIVITY_NAME = applicationId + "loginactivityname";//token类型
        public static final String TOKEN_ACTIVITY_TYPE = applicationId + "TokenactivityType";//token类型


    }

    /**
     * okhttp联网请求常量
     */
    public static class ConstantOkhttp {
        public static final long MAXSIZE = 1024 * 1024 * 10; //缓存
        public static final String MEDIA_TYPE_PNG = "image/png";//上传图片格式

    }

    /**
     * 工具常量
     */
    public static class ConstantTools {
        public static final String PHOTO_SAVE_IMAGE = "/测试";//用户图片保存文件名字
    }

    /**
     * SQLite常量参数类
     */
    public static class ConstantSqlite {
        public static final String DB_HELPER_NAME = "createba";//数据库name
        public static final int DB_HELPER_VERSION = 1;//数据库版本


        public static final String SQL_COLUMN_ID = "_id";//sql数据库规定id为_id
        public static final String SQL_HEAD_URI = "content://";//sql数据库规定uri头部为content://
    }

}
