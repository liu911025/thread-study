package com.example.demotest.xml;

import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import java.util.Iterator;

public class Demo4jParse {

    @Test
    public void Test_01() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<root> \n" +
                "  <code>0000</code>\n" +
                "  <data>返回数据</data>\n" +
                "  <message>提示，错误的时候有错误原因的提示</message>\n" +
                "  <error>错误代码</error>\n" +
                "  <requestId></requestId>\n" +
                "</root>";

        boolean b = parse4DOM4j(xml);
        System.out.println(b);
    }
    @Test
    public void Test_02() {
        /*String s = "123";
        String s2 = JSON.toJSONString(s);
        System.out.println(s.equals(s2));*/
        Object s = "123";
        if (s instanceof String) {
            System.out.println(111);
        }
    }

    @Test
    public void  test_03() {
        String url = "WS@http://172.16.12.223:8883/His-service/HisEntranceWs?wsdl@uploadCaStaffSignature";
        String[] urlArr = url.split("@");

        String urlMehtod = urlArr[1] + urlArr[2];
        System.out.println(urlMehtod);
    }

    private static boolean parse4DOM4j(String xmlStr){
        try {
            org.dom4j.Document doc = (org.dom4j.Document) DocumentHelper.parseText(xmlStr);
            org.dom4j.Element message = doc.getRootElement();
            System.out.println("根节点" + message.getName());
            Iterator elements = message.elementIterator();
            while(elements.hasNext()){
                org.dom4j.Element element = (org.dom4j.Element)elements.next();
                String name = element.getName();
                String value = element.getText();
                System.out.println("节点:" + name + "--> value:" + value);
                if ("code".equals(name) && "00000".equals(value)) {
                    return true;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;
    }

}
