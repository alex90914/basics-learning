package com.dream.basics.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-08-06 10:32
 * @desc
 */
public class FileCompressUtils {

    public static void main(String[] args) throws Exception {
        File fromPic = new File("e:/aaa.jpeg");
        File toPic = new File("e:/bbb.jpeg");
        Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
    }

}
