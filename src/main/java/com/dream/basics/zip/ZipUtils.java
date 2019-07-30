package com.dream.basics.zip;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-07-29 15:04
 * @desc zip压缩工具类
 */
public class ZipUtils {
    /**
     * 基目录
     */
    private static final String BASE_DIR = "";
    /**
     * 缓冲区大小
     */
    private static final int BUFFER = 2048;
    /**
     * 字符集
     */
    private static final String CHAR_SET = "UTF-8";

    /**
     * 描述:将文件压缩到zip中
     *
     * @param file            文件
     * @param zipOutputStream zip输出流
     * @param dir             基目录
     * @throws Exception
     */

    private static void compressFile(File file, ZipOutputStream zipOutputStream, String dir) throws Exception {
        InputStream in = null;
        BufferedInputStream bis = null;
        try {
            // 压缩文件
            ZipEntry zipEntry = new ZipEntry(dir + file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            // 读取文件
            in = new FileInputStream(file);
            bis = new BufferedInputStream(in);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                zipOutputStream.write(data, 0, count);
            }
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 将多个文件压缩为zip文件,供页面下载
     *
     * @param files    文件
     * @param response 响应
     * @param filename 文件名
     */
    public static void downloadZip(List<File> files, HttpServletResponse response, String filename) {
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        OutputStream out = null;
        ZipOutputStream zipOutputStream = null;
        try {
            out = response.getOutputStream();
            zipOutputStream = new ZipOutputStream(out);
            zipOutputStream.setEncoding(CHAR_SET);
            for (int i = 0; i < files.size(); i++) {
                compressFile(files.get(i), zipOutputStream, BASE_DIR);
            }
            zipOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
