package com.example.administrator.createba.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间操作类
 * Created by C.jiuxu on 2015/5/25.
 */
public class DateTools {


    /**
     * 获得当前时间格式化指定字符串
     * @param format : 时间格式  如yyyyMMddHHmmssSS
     * @return 格式化的时间字符串
     */
    public static String getDateToString(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String s = simpleDateFormat.format(date);
        date = null;
        simpleDateFormat = null;
        return s;
    }

    /**
     * 格式化字符串为dated
     * @param format 时间格式
     * @param s      要格式的字符串
     * @return Date类
     * @throws ParseException
     */
    public static Date formatStringToDate(String format, String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(s);
    }

}
