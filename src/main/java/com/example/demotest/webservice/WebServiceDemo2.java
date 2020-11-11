package com.example.demotest.webservice;

import com.alibaba.fastjson.JSON;
import org.apache.cxf.common.i18n.UncheckedException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebServiceDemo2 {

    public static void main(String[] args) throws Exception {
        /*String wsdlUrl = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
        String methodName = "getMobileCodeInfo";
        String targetNamespace = "http://WebXml.com.cn/";
        String name = "MobileCodeWS";
        String param = "17328762512";
        Object[] params = new Object[2];

        String  mobileCode = "17328762512";
        String userID = "";
        params[0] = mobileCode;
        params[1] = userID;
        //Object s = cxfWsCall(wsdlUrl, methodName, params);
        List<Object> paramObj = new ArrayList<>();
        paramObj.add(mobileCode);
        paramObj.add("");
        Object s = cxfWsCall(wsdlUrl, methodName, paramObj);
        System.out.println(s);*/

        Object[] params = new Object[2];

        String  mobileCode = "13888888888";
        String userID = "";
        params[0] = mobileCode;
        params[1] = userID;
        try {
            Object res = cxfWsCall("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl","getDatabaseInfo",mobileCode);
            if (res instanceof Object[]) {
                Object[] arr = (Object[]) res;
                for (Object obj : arr) {
                    System.out.println(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Object cxfWsCall(String wsUrl,String methodName,Object paramObj) throws Exception {
        Client client = null;
        Object[] res = null;
        try{
            //CXF调用webservice客户端   第一种：
            //生成动态客户端
            JaxWsDynamicClientFactory dynamicClient = JaxWsDynamicClientFactory.newInstance();
            client = dynamicClient.createClient(wsUrl);
            //设置超时时间
            HTTPConduit http = (HTTPConduit) client.getConduit();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(5000);  //连接超时
            httpClientPolicy.setAllowChunking(false);    //取消块编码
            httpClientPolicy.setReceiveTimeout(20000);     //响应超时
            http.setClient(httpClientPolicy);
            //调用，并返回结果
            res = client.invoke(methodName, paramObj);
        } catch(UncheckedException uncheckEx) {
            throw new SocketTimeoutException("获取服务【"+wsUrl+"】方法【"+methodName+"】失败："+uncheckEx.getMessage());
        } catch (Exception e) {
            throw new SocketTimeoutException("调用webservices【"+wsUrl+"】,方法名称【"+methodName+"】失败："+e.getMessage());
        } finally {
            client.destroy();
        }
        return res;
    }
    
}
