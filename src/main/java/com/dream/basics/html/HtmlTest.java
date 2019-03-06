package com.dream.basics.html;


import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-02-26 11:28
 * @desc
 */
public class HtmlTest {
    public static void main(String[] args) {
        String html = "<p>吾问无为谓无无无无无无无</p><p>吾问无为谓无无无无</p><p>&nbsp;吾问无为谓无无无无无无</p><p>吾问无为谓无无无无无无无</p>";
        String unescapeHtml = StringEscapeUtils.unescapeHtml(html);
        System.out.println(unescapeHtml);
        String HTMLText = "<p>我的<br/>评论</p>";
        System.out.println(StringEscapeUtils.escapeHtml(HTMLText));


    }
}
