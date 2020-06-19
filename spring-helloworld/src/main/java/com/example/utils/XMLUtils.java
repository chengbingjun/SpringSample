package com.example.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

public class XMLUtils {

    /**
     * XML字符串转实体类
     */
    public static <T> T toObj(Class<T> clazz, String xml) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(clazz);
        T t = (T) xStream.fromXML(xml);
        return t;
    }

    /**
     * 实体类转XML字符串
     */
    public static String toXml(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        // 扫描@XStream注解
        xStream.processAnnotations(obj.getClass());
        //正则过滤双下划线转为单下划线
        return xStream.toXML(obj).replaceAll("\\_+", "_");
    }

    /**
     *
     * @Description: 获取xml格式字符串中指定的元素标签片段
     * @param xml：xml格式的字符串
     * @param elementId：节点名
     * @return
     */
    public static String getElementString(String xml, String elementId) {
        if (StringUtils.isBlank(xml) || StringUtils.isBlank(elementId)) {
            return null;
        }
        // 获取document对象
        Document document = Jsoup.parse(xml, "", new Parser(new XmlTreeBuilder()));
        // 获取对应的document片段
        //return document.select(elementId).toString().replaceAll("\\s*", "");
        Elements element = document.select(elementId);
        return element.toString().replaceAll("\\s*", "");
    }

    /**
     *
     * @Description: xml字符串节点片段转对象
     * @param xml
     * @param elementId：节点名
     * @param clazz
     * @return
     */
    public static <T> T parseElementObj(String xml, String elementId, Class<T> clazz) {
        if (StringUtils.isBlank(xml) || StringUtils.isBlank(elementId)) {
            return null;
        }
        return toObj(clazz, getElementString(xml, elementId));
    }
}

