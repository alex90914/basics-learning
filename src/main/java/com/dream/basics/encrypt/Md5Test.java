package com.dream.basics.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-02-25 17:24
 * @desc
 */
public class Md5Test {
    public static void main(String[] args) throws Exception {
        System.out.println(getMD5("e:/audio.amr"));
        InputStream in = new FileInputStream(new File("e:/audio.amr"));
        System.out.println(DigestUtils.md5Hex(in));
    }


    /**
     * #######################
     * #AppKey：8DD389FB28B94A598527F51943A02B68
     * #AppSecret：A269911BF87D40B78EE596FAE2069670
     * #appKey	String	N		应用Key，创建应用时生成，可在应用详情中查看
     * #timestamp	String	N		Unix毫秒时间戳
     * #sign	String	N		MD5-32(appKey+timestamp+appSecret)，MD5加密，32位小写
     */
//1552297014727
    @Test
    public void test() {
        String AppKey = "8DD389FB28B94A598527F51943A02B68";
        String AppSecret = "A269911BF87D40B78EE596FAE2069670";
        long timestamp = 1552297014727L;
        System.out.println(DigestUtils.md5Hex(AppKey + timestamp + AppSecret));
        System.out.println("9b97ce269c41a82705af2b16fa9f1700".length());
    }


    public static String getMD5(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }
}
