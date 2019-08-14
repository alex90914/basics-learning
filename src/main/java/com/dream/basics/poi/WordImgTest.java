package com.dream.basics.poi;

import org.apache.commons.io.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-08-09 10:13
 * @desc
 */
public class WordImgTest {
    public static void main(String[] args) throws Exception {
        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph title = doc.createParagraph();
        XWPFRun run = title.createRun();
        run.setText("Fig.1 A Natural Scene");
        run.setBold(true);
        title.setAlignment(ParagraphAlignment.CENTER);
        String imgFile = "e:/aaa.jpeg";
        FileInputStream is = new FileInputStream(imgFile);
        run.addBreak();
        run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(200), Units.toEMU(200));
        is.close();
        FileOutputStream fos = new FileOutputStream("e:/test4.docx");
        doc.write(fos);
        fos.close();
    }
}
