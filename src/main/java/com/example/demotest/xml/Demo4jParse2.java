package com.example.demotest.xml;

import com.alibaba.fastjson.JSON;
import com.itextpdf.xmp.impl.Base64;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ljt
 * @version 1.0
 * @description:
 * @date 2021/6/23 14:42
 */
public class Demo4jParse2 {

    static String xml = "<Message id=\"128\" code=\"EmrDocAr\">\n" +
            "\t<DOCUMENT>\n" +
            "\t\t<MEDICAL_INSTITUT_CODE>医疗机构组织机构代码（参考附表 g）</MEDICAL_INSTITUT_CODE>\n" +
            "\t\t<DOCUMENT_PRODUCER>生产系统，值域参照【HT01.00.001 电子病历文档生产系统代码】</DOCUMENT_PRODUCER>\n" +
            "\t\t<UNIQUE_ID>文档唯一标识</UNIQUE_ID>\n" +
            "\t\t<FILE_NUMBER>患者档案号（取PatRowID）</FILE_NUMBER>\n" +
            "\t\t<VISITING_SERIAL_NUMBER>就诊流水号（取AdmNo）</VISITING_SERIAL_NUMBER>\n" +
            "\t\t<HOSPITALIZATION_NUMBER>住院号/门诊号（取AdmSerialNum）</HOSPITALIZATION_NUMBER>\n" +
            "\t\t<HOSPITALIZATION_TIMES>住院次数</HOSPITALIZATION_TIMES>\n" +
            "\t\t<MEDICAL_RECORD_NUMBER>住院病案号（取DocumentID）</MEDICAL_RECORD_NUMBER>\n" +
            "\t\t<DOCUMENT_CATEGORY>文档分类，值域参照【HT01.00.002电子病历文档分类】</DOCUMENT_CATEGORY>\n" +
            "\t\t<DOCUMENT_TOPIC>文档主题，值域参照【HT01.00.003电子病历主题】</DOCUMENT_TOPIC>\n" +
            "\t\t<DOCUMENT_TITLE>文档标题</DOCUMENT_TITLE>\n" +
            "\t\t<SHOW_ORDER>显示排序号</SHOW_ORDER>\n" +
            "\t\t<FILE_COUNT>归档文件总数</FILE_COUNT>\n" +
            "\t\t<BASE64>文件Base64编码（单文件）</BASE64>\n" +
            "\t\t<ORIGINAL_URL>文件原始地址（多文件时，所有文件必须统一格式)</ORIGINAL_URL>\n" +
            "\t\t<!--BASE64和ORIGINAL_URL必选其一-->\n" +
            "\t\t<FILE_EXTENSION>指定文件扩展名（为空则自动判断）</FILE_EXTENSION>\n" +
            "\t\t<CREATE_DATETIME>文档创建日期时间</CREATE_DATETIME>\n" +
            "\t\t<UPDATE_DATETIME>文档最后修改日期时间</UPDATE_DATETIME>\n" +
            "\t\t<SIGN_LIST>\n" +
            "\t\t\t<![CDATA[\n" +
            "          <SIGNS>\n" +
            "            <!--签名列表-->\n" +
            "            <SIGN final=\"1\">\n" +
            "              <!--总签名人final属性置1，否则置0-->\n" +
            "              <SIGN_CODE>CA签名人代码</SIGN_CODE>\n" +
            "              <SIGN_NAME>CA签名人姓名</SIGN_NAME>\n" +
            "              <SIGN_CERT type=\"证书类型\">CA签名人证书</SIGN_CERT>\n" +
            "              <SIGN_VALUE>CA签名值（P7）</SIGN_VALUE>\n" +
            "              <SIGN_DATETIME>文档签署日期时间（时间戳）</SIGN_DATETIME>\n" +
            "            </SIGN>\n" +
            "            <!--多个签名则多个SIGN节点，按签署日期顺序排序-->\n" +
            "          </SIGNS>\n" +
            "        ]]>\n" +
            "\t\t</SIGN_LIST>\n" +
            "\t</DOCUMENT>\n" +
            "</Message>";


    static String cancelXml = "<Message id=\"129\" code=\"EmrDocArCl\">\n" +
            "\t<DOCUMENT>\n" +
            "\t\t<MEDICAL_INSTITUT_CODE>医疗机构组织机构代码（参考附表 g）</MEDICAL_INSTITUT_CODE>\n" +
            "\t\t<DOCUMENT_PRODUCER>生产系统（参考附表 b）</DOCUMENT_PRODUCER>\n" +
            "\t\t<UNIQUE_ID>文档唯一标识</UNIQUE_ID>\n" +
            "\t\t<OPER_CODE>撤销人代码</OPER_CODE>\n" +
            "\t\t<OPER_NAME>撤销人姓名</OPER_NAME>\n" +
            "\t</DOCUMENT>\n" +
            "</Message>";

    private static String setValueToXml(String xmlStr){
        try {
            org.dom4j.Document doc = (org.dom4j.Document) DocumentHelper.parseText(xmlStr);
            org.dom4j.Element message = doc.getRootElement();
            System.out.println("根节点" + message.getName());
            Element document = message.element("DOCUMENT");
            document.element("FILE_COUNT").setText("1");
            document.element("BASE64").setText("123");
            document.element("FILE_EXTENSION").setText("pdf");
            document.element("CREATE_DATETIME").setText("2021-06-23 10:00:00");
            document.element("UPDATE_DATETIME").setText("2021-06-23 14:54:00");
            String newXml = doc.asXML();
            return newXml;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void Test_01() {
        String newXml = setValueToXml(xml);
        System.out.println(newXml);
    }

    @Test
    public void Test_02() {
        String newXml = Base64.encode(xml);
        System.out.println(newXml);
    }

    @Test
    public void Test_03() {
        String base64Xml = "PE1lc3NhZ2UgaWQ9IjEyOCIgY29kZT0iRW1yRG9jQXIiPgoJPERPQ1VNRU5UPgoJCTxNRURJQ0FMX0lOU1RJVFVUX0NPREU+5Yy755aX5py65p6E57uE57uH5py65p6E5Luj56CB77yI5Y+C6ICD6ZmE6KGoIGfvvIk8L01FRElDQUxfSU5TVElUVVRfQ09ERT4KCQk8RE9DVU1FTlRfUFJPRFVDRVI+55Sf5Lqn57O757uf77yM5YC85Z+f5Y+C54Wn44CQSFQwMS4wMC4wMDEg55S15a2Q55eF5Y6G5paH5qGj55Sf5Lqn57O757uf5Luj56CB44CRPC9ET0NVTUVOVF9QUk9EVUNFUj4KCQk8VU5JUVVFX0lEPuaWh+aho+WUr+S4gOagh+ivhjwvVU5JUVVFX0lEPgoJCTxGSUxFX05VTUJFUj7mgqPogIXmoaPmoYjlj7fvvIjlj5ZQYXRSb3dJRO+8iTwvRklMRV9OVU1CRVI+CgkJPFZJU0lUSU5HX1NFUklBTF9OVU1CRVI+5bCx6K+K5rWB5rC05Y+377yI5Y+WQWRtTm/vvIk8L1ZJU0lUSU5HX1NFUklBTF9OVU1CRVI+CgkJPEhPU1BJVEFMSVpBVElPTl9OVU1CRVI+5L2P6Zmi5Y+3L+mXqOiviuWPt++8iOWPlkFkbVNlcmlhbE51be+8iTwvSE9TUElUQUxJWkFUSU9OX05VTUJFUj4KCQk8SE9TUElUQUxJWkFUSU9OX1RJTUVTPuS9j+mZouasoeaVsDwvSE9TUElUQUxJWkFUSU9OX1RJTUVTPgoJCTxNRURJQ0FMX1JFQ09SRF9OVU1CRVI+5L2P6Zmi55eF5qGI5Y+377yI5Y+WRG9jdW1lbnRJRO+8iTwvTUVESUNBTF9SRUNPUkRfTlVNQkVSPgoJCTxET0NVTUVOVF9DQVRFR09SWT7mlofmoaPliIbnsbvvvIzlgLzln5/lj4LnhafjgJBIVDAxLjAwLjAwMueUteWtkOeXheWOhuaWh+aho+WIhuexu+OAkTwvRE9DVU1FTlRfQ0FURUdPUlk+CgkJPERPQ1VNRU5UX1RPUElDPuaWh+aho+S4u+mimO+8jOWAvOWfn+WPgueFp+OAkEhUMDEuMDAuMDAz55S15a2Q55eF5Y6G5Li76aKY44CRPC9ET0NVTUVOVF9UT1BJQz4KCQk8RE9DVU1FTlRfVElUTEU+5paH5qGj5qCH6aKYPC9ET0NVTUVOVF9USVRMRT4KCQk8U0hPV19PUkRFUj7mmL7npLrmjpLluo/lj7c8L1NIT1dfT1JERVI+CgkJPEZJTEVfQ09VTlQ+5b2S5qGj5paH5Lu25oC75pWwPC9GSUxFX0NPVU5UPgoJCTxCQVNFNjQ+5paH5Lu2QmFzZTY057yW56CB77yI5Y2V5paH5Lu277yJPC9CQVNFNjQ+CgkJPE9SSUdJTkFMX1VSTD7mlofku7bljp/lp4vlnLDlnYDvvIjlpJrmlofku7bml7bvvIzmiYDmnInmlofku7blv4Xpobvnu5/kuIDmoLzlvI8pPC9PUklHSU5BTF9VUkw+CgkJPCEtLUJBU0U2NOWSjE9SSUdJTkFMX1VSTOW/hemAieWFtuS4gC0tPgoJCTxGSUxFX0VYVEVOU0lPTj7mjIflrprmlofku7bmianlsZXlkI3vvIjkuLrnqbrliJnoh6rliqjliKTmlq3vvIk8L0ZJTEVfRVhURU5TSU9OPgoJCTxDUkVBVEVfREFURVRJTUU+5paH5qGj5Yib5bu65pel5pyf5pe26Ze0PC9DUkVBVEVfREFURVRJTUU+CgkJPFVQREFURV9EQVRFVElNRT7mlofmoaPmnIDlkI7kv67mlLnml6XmnJ/ml7bpl7Q8L1VQREFURV9EQVRFVElNRT4KCQk8U0lHTl9MSVNUPgoJCQk8IVtDREFUQVsKICAgICAgICAgIDxTSUdOUz4KICAgICAgICAgICAgPCEtLeetvuWQjeWIl+ihqC0tPgogICAgICAgICAgICA8U0lHTiBmaW5hbD0iMSI+CiAgICAgICAgICAgICAgPCEtLeaAu+etvuWQjeS6umZpbmFs5bGe5oCn572uMe+8jOWQpuWImee9rjAtLT4KICAgICAgICAgICAgICA8U0lHTl9DT0RFPkNB562+5ZCN5Lq65Luj56CBPC9TSUdOX0NPREU+CiAgICAgICAgICAgICAgPFNJR05fTkFNRT5DQeetvuWQjeS6uuWnk+WQjTwvU0lHTl9OQU1FPgogICAgICAgICAgICAgIDxTSUdOX0NFUlQgdHlwZT0i6K+B5Lmm57G75Z6LIj5DQeetvuWQjeS6uuivgeS5pjwvU0lHTl9DRVJUPgogICAgICAgICAgICAgIDxTSUdOX1ZBTFVFPkNB562+5ZCN5YC877yIUDfvvIk8L1NJR05fVkFMVUU+CiAgICAgICAgICAgICAgPFNJR05fREFURVRJTUU+5paH5qGj562+572y5pel5pyf5pe26Ze077yI5pe26Ze05oiz77yJPC9TSUdOX0RBVEVUSU1FPgogICAgICAgICAgICA8L1NJR04+CiAgICAgICAgICAgIDwhLS3lpJrkuKrnrb7lkI3liJnlpJrkuKpTSUdO6IqC54K577yM5oyJ562+572y5pel5pyf6aG65bqP5o6S5bqPLS0+CiAgICAgICAgICA8L1NJR05TPgogICAgICAgIF1dPgoJCTwvU0lHTl9MSVNUPgoJPC9ET0NVTUVOVD4KPC9NZXNzYWdlPg==";
        String newXml = Base64.decode(base64Xml);
        System.out.println(newXml);
    }


    @Test
    public void Test_04() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("oldList:" + JSON.toJSONString(list));

        for (Integer i : list) {
            if (i % 2 == 0) {
                list.remove(i);
            }
        }

        System.out.println("newList:" + JSON.toJSONString(list));
    }

   @Test
    public void test_05() throws IOException {
        File file = new File("E:\\jar\\soap.txt");
       String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
       byte[] encode = Base64.encode(content.getBytes("UTF-8"));
       String base64 = new String(encode, "UTF-8");
       System.out.println(base64);
       /*org.apache.tomcat.util.codec.binary.Base64 base64 = new org.apache.tomcat.util.codec.binary.Base64();
       String base64Str = base64.encodeAsString(content.getBytes("UTF-8"));
       System.out.println(base64Str);*/
   }

    @Test
    public void test_06() throws IOException {
        File file = new File("E:\\jar\\xml.txt");
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
       /* org.apache.tomcat.util.codec.binary.Base64 base64 = new org.apache.tomcat.util.codec.binary.Base64();
        byte[] decode = base64.decode(content);
        String base64Str = new String(decode, "UTF-8");
        System.out.println(base64Str);*/
        byte[] decode = Base64.decode(content.getBytes("UTF-8"));
        String base64Str = new String(decode, "UTF-8");
        System.out.println(base64Str);
    }


}
