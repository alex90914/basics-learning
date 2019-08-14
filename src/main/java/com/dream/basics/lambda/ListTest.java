package com.dream.basics.lambda;

import com.dream.basics.vo.Emp;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
    List<Emp> empList = new ArrayList<>();


    @Before
    public void before() {
        list1.add(1);
        list1.add(2);
        list1.add(3);
        //====================
        list2.add(3);
        list2.add(4);
        list2.add(5);
        //============
        empList.add(new Emp(1));
        empList.add(new Emp(2));
        empList.add(new Emp(3));
        empList.add(new Emp(4));
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


    /**
     * 求和
     */
    @Test
    public void sum() {
        Integer sum = new ArrayList<Integer>().stream().reduce(Integer::sum).orElse(1000);
        System.out.println(sum);
        int ages = empList.stream().mapToInt(emp -> emp.getAge()).sum();
        System.out.println(ages);
    }


    /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    @Test
    public void listToMap() {
        Map<Integer, Emp> empMap = empList.stream().collect(Collectors.toMap(Emp::getAge, a -> a, (k1, k2) -> k1));
        System.out.println(empMap);
    }


    @Test
    public void groupingBy() {
        Map<Integer, List<Emp>> listMap = empList.stream().collect(Collectors.groupingBy(Emp::getAge));
        System.out.println(listMap);
    }


    /**
     * c2-01-ab-00-03-4a
     * c2-01-ab-00-03-11
     * c2-01-ab-00-03-12
     * c2-01-ab-00-03-55
     * c2-02-2e-00-00-20
     * c2-02-2e-00-00-22
     * c2-02-2e-00-00-27
     * c2-02-2e-00-00-00
     */

    @Test
    public void groupingTest2() {
        String bt = "5:c2-01-ab-00-03-4a,-12,c2-01-ab-00-03-11,-13,c2-01-ab-00-03-12,-14,c2-01-ab-00-03-55,-15,c2-02-2e-00-00-20,-16";
        String[] split = bt.split(":")[1].split(",");
        List<String> strings = Arrays.asList(split);
        List<List<String>> lists = splitToPieces(strings, 2);
        for (List<String> list : lists) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }


    /**
     * 将size为M的集合以每eachPieceSize为单位分隔成N组
     *
     * @param data          数据
     * @param eachPieceSize 每组的数量
     * @param <T>           泛型
     * @return N个分组
     */
    public static <T> List<List<T>> splitToPieces(Collection<T> data, int eachPieceSize) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>(0);
        }
        if (eachPieceSize <= 0) {
            throw new IllegalArgumentException("参数错误");
        }
        List<List<T>> result = new ArrayList<>();
        for (int index = 0; index < data.size(); index += eachPieceSize) {
            result.add(data.stream().skip(index).limit(eachPieceSize).collect(Collectors.toList()));
        }
        return result;
    }


}
