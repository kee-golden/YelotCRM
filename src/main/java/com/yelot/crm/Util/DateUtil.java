package com.yelot.crm.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kee on 16/12/7.
 */
public class DateUtil {
    public static Date toDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getFisrtDay(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = sdf.format(c.getTime());
        return first;
    }

    public static String getLastDay(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = sdf.format(ca.getTime());
        return last;
    }

    public static String getFirstWee(String format) {

        Calendar cal = Calendar.getInstance();
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        return new SimpleDateFormat(format).format(cal.getTime());
    }


    public static String getLastWee(String format) {
        Calendar cal = Calendar.getInstance();
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    //获取该日期后一天
    public static String getNextDay(String endTm, String format) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(f.parse(endTm).getTime() + 24 * 3600 * 1000);
        endTm = f.format(d);
        return endTm;
    }

    public static void main(String[] argv) {
        String dateStr = "2016-12-01 22:33";
        String format = "yyyy-MM-dd HH:mm";
        Date now = new Date();
        System.out.println(DateUtil.toDate(dateStr, format));
        System.out.println(DateUtil.toString(now, format));
    }
}
