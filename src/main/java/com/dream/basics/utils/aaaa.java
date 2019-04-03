package com.dream.basics.utils;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-03-15 11:31
 * @desc
 */
public class aaaa {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    /**
     * 判断一个点是否在圆形区域内
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, String radius) {
        double distance = getDistance(lng1, lat1, lng2, lat2);
        double r = Double.parseDouble(radius);
        System.out.println(distance);
        if (distance > r) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        double lng1 = 120.39650917053223;
        double lat1 = 36.30710217895976;
        double lng2 = 120.33577170506202;
        double lat2 = 36.33504467098185;
        String radius = "124.15347189233752";
        System.out.println(isInCircle(lng2, lat2, lng1, lat1, radius));
    }


}
