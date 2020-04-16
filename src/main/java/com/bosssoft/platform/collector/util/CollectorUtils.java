package com.bosssoft.platform.collector.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CollectorUtils {

    public static String getAfterMonth(String inputDate, int number) {
        Calendar c = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(inputDate);// 初始日期
        } catch (Exception e) {

        }
        c.setTime(date);// 设置日历时间
        c.add(Calendar.MONTH, number);// 在日历的月份上增加指定个月
        String strDate = sdf.format(c.getTime());// 得到你想要得6个月后的日期
        return strDate;
    }

    public static String getCurrentTimeStr() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

}
