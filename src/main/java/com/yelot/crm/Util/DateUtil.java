package com.yelot.crm.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kee on 16/12/7.
 */
public class DateUtil {
    public static Date toDate(String dateStr, String format) {
        if(dateStr == null || dateStr.isEmpty()){
            return null;
        }
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


    //以下是转化函数
    public static List<String> getDayBetweenStartAndEnd(String startStr, String endStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startStr.replace("/", "-"));
        Date endDate = sdf.parse(endStr.replace("/", "-"));
        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(startDate);
        while (startDate.getTime() <= endDate.getTime()) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            startDate = tempStart.getTime();
        }
        return result;
    }

    public static  List<String> getWeekBetweenStartAndEnd(String startStr, String endStr) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(startStr.replace("/", "-"));
        Date endDate = format.parse(endStr.replace("/", "-"));

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        endCalendar.setTime(endDate);

        List<String> result = new ArrayList<String>();

        for (int i = startCalendar.get(Calendar.WEEK_OF_YEAR); i <= endCalendar.get(Calendar.WEEK_OF_YEAR); i++) {
            result.add(startCalendar.get(Calendar.YEAR) + "-" + String.format("%02d", i - 1));
        }

        return result;
    }

    public static  List<String> getMonthBetweenStartAndEnd(String startStr, String endStr) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(startStr.replace("/", "-"));
        Date endDate = format.parse(endStr.replace("/", "-"));

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        List<String> result = new ArrayList<String>();

        for (int i = startCalendar.get(Calendar.MONTH); i <= endCalendar.get(Calendar.MONTH); i++) {
            result.add(startCalendar.get(Calendar.YEAR) + "-" + String.format("%02d", i+1));
        }
        return result;
    }

    public static void main(String[] argv) {
        String dateStr = "2016-12-01 22:33";
        String format = "yyyy-MM-dd HH:mm";
        Date now = new Date();
        System.out.println(DateUtil.toDate(dateStr, format));
        System.out.println(DateUtil.toString(now, format));
    }
}
