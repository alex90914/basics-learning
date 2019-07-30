package com.dream.basics.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-22 15:03
 * @desc
 */
public class SetTest {
    public static void main(String[] args) {
        List<Integer> a  = new ArrayList<>();
        a.add(1);
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(2);
        a.add(2);
        List<Integer> collect = a.stream().distinct().collect(Collectors.toList());
        collect.forEach(b-> System.out.println(b));

    }
}
