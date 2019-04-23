package com.dream.basics.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-04-23 11:49
 * @desc https://blog.csdn.net/lu930124/article/details/77595585/
 */
public class ListTest {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    @Before
    public void before() {
        list1.add(1);
        list1.add(2);
        list1.add(3);
        //====================
        list2.add(3);
        list2.add(4);
        list2.add(5);
    }


    /**
     * 并集
     */
    @Test
    public void union() {
        List<Integer> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list = list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    /**
     * 差集
     */
    @Test
    public void difference() {
        List<Integer> list = list1.stream().filter(t -> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

    }

    /**
     * 交集
     */
    @Test
    public void intersection() {
        List<Integer> list = list1.stream().filter(t -> list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }
}
