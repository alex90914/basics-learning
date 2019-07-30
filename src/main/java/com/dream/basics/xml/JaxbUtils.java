package com.dream.basics.xml;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-04-04 16:14
 * @desc jaxb xml工具类
 */


import com.dream.basics.vo.Dept;
import com.dream.basics.vo.Emp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WuBo
 * @email 343618924@qq.com
 * @create 2019-04-08 10:14
 * @desc JAXB xml解析
 */
public class JaxbUtils {
    private JaxbUtils() {
    }

    private static ConcurrentHashMap<String, JAXBContext> jaxbContextMap = new ConcurrentHashMap<>();
    private static final String ENCODING_GB2312 = "GB2312";


    public static String toXML(Object javaBean) {
        return toXML(javaBean, ENCODING_GB2312);
    }


    /**
     * java对象转换为xml
     *
     * @param javaBean
     * @param encoding
     * @return
     */
    public static <T> String toXML(T javaBean, String encoding) {
        try {
            JAXBContext jaxbContext = jaxbContextMap.get(javaBean.getClass().getName());
            if (jaxbContext == null) {
                jaxbContext = JAXBContext.newInstance(javaBean.getClass());
                jaxbContextMap.put(javaBean.getClass().getName(), jaxbContext);
            }
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            StringWriter writer = new StringWriter();
            marshaller.marshal(javaBean, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * xml转java实体类
     *
     * @param xml       xml字符串
     * @param valueType javaBean类型
     * @param <T>       javaBean对象
     * @return
     */
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext jaxbContext = jaxbContextMap.get(valueType.getName());
            if (jaxbContext == null) {
                jaxbContext = JAXBContext.newInstance(valueType);
                jaxbContextMap.put(valueType.getName(), jaxbContext);
            }
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Emp emp = new Emp("张三",12,new Dept(12L,"开发部"));
        System.out.println(toXML(emp));
    }

}
