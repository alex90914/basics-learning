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

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "张三\r\n我的名字<p>我的<br/>评论</p>");
        map.put("email", "123456@qq.com");
        getBuild("模板导出测试.doc", map, "e:/aaa.doc");
    }

    public static void getBuild(String tmpFile, Map<String, String> contentMap, String exportFile) {

        //    InputStream inputStream = DocUtil.class.getClassLoader().getResourceAsStream(tmpFile);
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