package com.dream.basics.jvm;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-03-07 11:08
 * @desc
 */
public class TestA {
    /**
     *
     *
     *-XX:
     *查看垃圾收集器日志
     *
     * -verbose:gc -XX:+PrintGCDetails
     * -Xmn指定新生代大小
     * @param args
     */
    public static void main(String[] args) {
        byte[] bytes = new byte[200 * 1204 * 1024];
    }
}
