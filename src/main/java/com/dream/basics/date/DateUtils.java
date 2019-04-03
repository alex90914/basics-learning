package com.dream.basics.date;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-02-20 11:49
 * @desc
 */
public class DateUtils {
    /**
     * 根据具体年份周数获取日期范围
     *
     * @param year
     * @param week
     * @param targetNum
     * @return
     */
    public static String getWeekDays(int year, int week, int targetNum) {
// 计算目标周数
        if (week + targetNum > 52) {
            year++;
            week += targetNum - 52;
        } else if (week + targetNum <= 0) {
            year--;
            week += targetNum + 52;
        } else {
            week += targetNum;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
// 设置每周的开始日期
        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String beginDate = sdf.format(cal.getTime());

        cal.add(Calendar.DAY_OF_WEEK, 6);
        String endDate = sdf.format(cal.getTime());

        return beginDate + "~" + endDate;
    }
//
//    public static void main(String[] args) {
//        // System.out.println(getWeekDays(2019, 1, 1));
//        //  List<String> dayByMonth = getDayByMonth(2019, 1);
//        //dayByMonth.forEach(item -> System.out.println(item));
////
////        List<String> dayByMonth = getMonthFullDay(2020, 5);
////        dayByMonth.forEach(item -> System.out.println(item));
//
//
//
//    }

    public static void main(String[] args) {
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            Date date = sdf1.parse("2018-01-22T09:12:43Z");//拿到Date对象
//            String str = sdf2.format(date);//输出格式：2017-01-22 09:28:33
//            System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String strDate = "2015-08-04";
//        LocalDate aLD = LocalDate.parse(strDate);
//        System.out.println("Date: " + aLD);

        String strDatewithTime = "2015-08-04T10:11:30";
//
//        LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
//        System.out.println("Date with Time: " + aLDT);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-'T'HH:mm:ssXXX");
        LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"

        System.out.println(formattedDateTime);

    }

    /**
     * java 获取 获取某年某月 所有日期（yyyy-mm-dd格式字符串）
     *
     * @param year
     * @param month
     * @return
     */
    public static List<String> getMonthFullDay(int year, int month) {
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
        List<String> fullDayList = new ArrayList<>(32);
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month - 1);
        // 当月1号
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 1; j <= count; j++) {
            fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return fullDayList;
    }


    public static List<String> getDayByMonth(int yearParam, int monthParam) {
        List list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.set(yearParam, monthParam, 1);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = null;
            if (month < 10 && i < 10) {
                aDate = String.valueOf(year) + "-0" + month + "-0" + i;
            }
            if (month < 10 && i >= 10) {
                aDate = String.valueOf(year) + "-0" + month + "-" + i;
            }
            if (month >= 10 && i < 10) {
                aDate = String.valueOf(year) + "-" + month + "-0" + i;
            }
            if (month >= 10 && i >= 10) {
                aDate = String.valueOf(year) + "-" + month + "-" + i;
            }

            list.add(aDate);
        }
        return list;
    }

    @Test
    public void aaa() throws Exception {
        String today = "2019-03-10";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }


}
