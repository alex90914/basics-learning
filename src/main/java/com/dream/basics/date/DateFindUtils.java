package com.dream.basics.date;

import com.dream.basics.utils.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-16 10:06
 * @desc
 */
public class DateFindUtils {

    public static void main(String[] args) {
        List<Date> list = new ArrayList<>();
        list.add(DateUtil.parseDate("2019-07-16 12:12:12", DateUtil.DATETIME24_PATTERN_LINE));
        list.add(DateUtil.parseDate("2019-07-16 14:18:12", DateUtil.DATETIME24_PATTERN_LINE));
        list.add(DateUtil.parseDate("2019-07-16 16:12:12", DateUtil.DATETIME24_PATTERN_LINE));
        list.add(DateUtil.parseDate("2019-07-16 18:12:12", DateUtil.DATETIME24_PATTERN_LINE));
        list.add(DateUtil.parseDate("2019-07-16 17:12:12", DateUtil.DATETIME24_PATTERN_LINE));
        list.add(DateUtil.parseDate("2019-07-16 19:12:12", DateUtil.DATETIME24_PATTERN_LINE));
        Date date = DateUtil.parseDate("2019-07-16 15:12:12", DateUtil.DATETIME24_PATTERN_LINE);
        System.out.println(DateUtil.formatDate(findRecentlyDate(list, date), DateUtil.DATETIME24_PATTERN_LINE));
        System.out.println(DateUtil.formatDate(find2(list, date), DateUtil.DATETIME24_PATTERN_LINE));
        Integer X = Integer.TYPE.getModifiers();
        Integer Y = Void.TYPE.getModifiers();
        System.out.println(X);
        System.out.println(Y);
        System.out.println(5 == X);

    }


    /**
     * 搜索离得最近的日期, 先排序再搜索, 修改下适合进行大量的快速搜索
     *
     * @param list
     * @param d
     * @return
     */
    private static Date findRecentlyDate(List<Date> list, Date d) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        list = new ArrayList<>(list);
        Collections.sort(list);
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cm = d.compareTo(list.get(mid));
            if (cm < 0) {
                right = mid - 1;
            } else if (cm > 0) {
                left = mid + 1;
            } else {
                return list.get(mid);
            }
        }
        if (left <= 0) {
            return list.get(0);
        }
        if (left >= list.size()) {
            return list.get(list.size() - 1);
        }
        long dLeft = d.getTime() - list.get(left - 1).getTime();
        long dRight = list.get(left).getTime() - d.getTime();
        return dLeft < dRight ? list.get(left - 1) : list.get(left);
    }


    /**
     * 搜索离得最近的日期.适合只进行一次的快速搜索.
     *
     * @param list
     * @param d
     * @return
     */
    private static Date find2(List<Date> list, Date d) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        long gap = Long.MAX_VALUE;
        Date r = null;
        long time = d.getTime();
        for (Date t : list) {
            long tm = Math.abs(time - t.getTime());
            if (gap > tm) {
                gap = tm;
                r = t;
            }
        }
        return r;
    }
}
