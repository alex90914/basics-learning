package com.dream.basics.poi;

/**
 * @author WuBo
 * @email wubo1990@aliyun.com
 * @create 2019-08-09 10:06
 * @desc
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class blog {
    public static void main(String[] args) throws Exception {
        //测试添加图片
        String sourceFile="D:/测试模板.docx";
        String targetFile="D:/test.docx";
        addStampImage(sourceFile, targetFile);
    }

    public static void addStampImage(String sourceFile, String targetFile) {
        XWPFDocument doc;
        try {
            doc = new XWPFDocument(new FileInputStream(sourceFile));
            for(XWPFTable table : doc.getTables()) {
                for(XWPFTableRow row : table.getRows()) {
                    //遍历每一个单元格
                    for(XWPFTableCell cell : row.getTableCells()) {
                        //如果遇到"&章"则进行替换
                        if(cell.getText().contains("&章")) {
                            try {
                                //给带有要盖章字样的单元格 加上章的图片
                                insertCellStamp(cell);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            FileOutputStream fos = new FileOutputStream(targetFile);
            doc.write(fos);
            fos.close();

            doc.write(new FileOutputStream(targetFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertCellStamp(XWPFTableCell cell) throws InvalidFormatException, IOException {
        //给带有要盖章字样的单元格 加上章的图片
        //存放要加入的图片
        List<String> stamps = new ArrayList<>();
        //图片的序号，从0开始
        int stampOrder = 0;
        //获取需要的图片，
        for(XWPFParagraph para :cell.getParagraphs()) {
            //从段落中获取要盖的章的名称
            String paraText = para.getText();
            if(paraText != null) {
                String[] split = para.getText().split(" ");
                for(String s : split) {
                    s = s.trim();
                    if(!s.isEmpty() ) {
                        //如：&章公章01.png，去掉标识符&章，只留下章的名字
                        stamps.add(s.replace("&章", ""));
                    }
                }
            }
        }

        String basedir = "E:";
        for(XWPFParagraph para :cell.getParagraphs()) {
            for (XWPFRun run : para.getRuns()) {
                //清空所有文字
                run.setText("", 0);
            }
            //插入图片
            for(int i = 0; i<stamps.size() && i<para.getRuns().size(); i++) {
                try {
                    XWPFRun run = para.getRuns().get(i);
                    String imgFile = basedir + "/公章管理/" + stamps.get(stampOrder++);
                    System.out.println("插入盖章图片:" + imgFile);
                    FileInputStream is = new FileInputStream(imgFile);
                    // 100x100 pixels
                    run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(100), Units.toEMU(100));
                    is.close();
                    run.setText("  ",0);
                } catch (Exception e) {
                    System.out.println("Error: ========  插入单个公章图片时出错了:可能是图片路径不存在。不影响主流程");
                    e.printStackTrace();
                }
            }
        }
    }
}

