package com.dream.basics.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-02-26 10:37
 * @desc
 */
public class DocUtil {


    public static String delHtmlTag(String str) {
        str = str.replace("</p>", "\r");
        str = str.replaceAll("<[.[^>]]*>", "").replaceAll(" ", "");
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "2019第10周安全周报");
        map.put("createName", "炸不死发布");
        map.put("createTime", "2018-12-12");
        map.put("projectName", "发的萨芬撒发顺丰");
        map.put("constructionSection", "AdaDADadAD");
        map.put("dangerCount", "1");
        map.put("practicalPercent", "25%");
        map.put("planCount", "1");
        map.put("practicalCount", "1");
        map.put("happenCount", "1");
        map.put("disposeCount", "1");
        map.put("suspendCount", "1");
        map.put("dangerDetails", "aaaaa");
        String jobSummary = "<p><b><i>我的老家就在那个屯</i></b></p><p><b><i>啊啊啊</i></b></p><p>啊啊</p><p>啊</p><p>啊</p><p>啊</p><p>啊</p><p>啊</p><p><br></p>";
        String delHtmlTag = delHtmlTag(jobSummary);
        map.put("jobSummary", delHtmlTag);
        map.put("nextWeekPlan", "QEQEQWEWQEWQEWQEWQEWQEJHKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        map.put("jobCooperate", "QEQEQWEWQEWQEWQEWQEWQEJHKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        map.put("superiorCheckOpinion", "QEQEQWEWQEWQEWQEWQEWQEJHKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        getBuild("安全周报模板.doc", map, "e:/aaa.doc");
    }


    public static void getBuild(String tmpFile, Map<String, String> contentMap, String exportFile) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(tmpFile);
        HWPFDocument document = null;
        try {
            document = new HWPFDocument(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }
        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);
            OutputStream outputStream = new FileOutputStream(exportFile);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}