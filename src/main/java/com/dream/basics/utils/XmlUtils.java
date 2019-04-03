package com.dream.basics.utils;

import com.alibaba.fastjson.JSONObject;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-03-18 17:58
 * @desc
 */


/**
 * @author watermelon_code
 * @version 1.0.0
 * @ClassName StaxonUtils
 * @Description 实现JSON--XML互转
 * @Date 2017年7月19日 上午10:49:48
 */
public class XmlUtils {
    /**
     * @Description: json string convert to xml string
     * @author watermelon_code
     * @date 2017年7月19日 上午10:50:32
     */
    public static String json2xml(String json) {
        StringReader input = new StringReader(json);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    /**
     * @Description: 去掉转换xml之后的换行和空格
     * @author watermelon_code
     * @date 2017年8月9日 下午4:05:44
     */
    public static String json2xmlReplaceBlank(String json) {
        String str = XmlUtils.json2xml(json);
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "斩杀那");
        jsonObject.put("age", "12");
        System.out.println(json2xmlReplaceBlank(jsonObject.toJSONString()));
    }
}
