package com.dream.basics.date;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-02-20 13:20
 * @desc
 */

import java.util.Calendar;

public class WeekUtils {

    public static void main(String[] args) {
        WeekUtils cd = new WeekUtils();
        System.out.println("开始时间:" + cd.getStartDayOfWeekNo(2019, 1));
        System.out.println("结束时间:" + cd.getEndDayOfWeekNo(2019, 1));

    }

    private Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    public String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);

    }

    public String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }
}


