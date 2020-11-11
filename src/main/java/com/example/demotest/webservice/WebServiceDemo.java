package com.example.demotest.webservice;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebServiceDemo {

    public static void main(String[] args) throws IOException {
        //第一步：创建服务地址，不是WSDL地址
        //URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
        URL url = new URL("http://localhost:8081/weatherService?wsdl");
        //第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //第三步：设置参数
        //3.1发送方式设置：POST必须大写
        connection.setRequestMethod("POST");
        //3.2设置数据格式：content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        //3.3设置输入输出，因为默认新创建的connection没有读写权限，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //第四步：组织SOAP数据，发送请求
        //String soapXML = getXML("17328702512");
        String soapXML = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:query xmlns:ns2=\"http://service.webservice.demo.com/\"><arg0>宜春</arg0><arg1>晴天</arg1></ns2:query></soap:Body></soap:Envelope>";
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());

        //第五步：接收服务端响应，打印（xml格式数据）
        int responseCode = connection.getResponseCode();
        if(200 == responseCode){//表示服务端响应成功
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            StringBuilder sb = null;
            try {
                is = connection.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                sb = new StringBuilder();
                String temp = null;
                while(null != (temp = br.readLine())){
                    sb.append(temp);
                }
                System.out.println(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                br.close();
                isr.close();
                is.close();
            }
        }

        os.close();
    }

    public static String getXML(String phoneNum) {
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<soap:Body>"
                +"<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"
                +"<mobileCode>"+phoneNum+"</mobileCode>"
                +"<userID></userID>"
                +"</getMobileCodeInfo>"
                +"</soap:Body>"
                +"</soap:Envelope>";
        return soapXML;
    }

}
