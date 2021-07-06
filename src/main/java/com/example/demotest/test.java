package com.example.demotest;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.example.demotest.pojo.Car;
import com.example.demotest.pojo.SignFile;
import com.example.demotest.service.Defaulable;
import com.example.demotest.service.DefaulableFactory;
import com.example.demotest.service.impl.DefaulableImpl;
import com.example.demotest.service.impl.OverridableImpl;
import com.example.demotest.util.Base64Util;
import com.example.demotest.util.DecryptionZipUtil;
import com.example.demotest.util.EncKeyUtil;
import com.example.demotest.util.ImageUtil;
import com.example.demotest.vo.RespData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.impl.AESDecrypter;
import de.idyl.winzipaes.impl.AESDecrypterBC;
import de.idyl.winzipaes.impl.ExtZipEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.dom4j.DocumentHelper;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import sun.misc.Unsafe;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

public class test {

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode =
            new AtomicInteger(16);


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.toArray();
        String[] strs = new String[list.size()];
        list.toArray(strs);
        System.out.println(strs.toString());

        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));

        int num = 1999;
        int count = 2000;

        System.out.println("index : " + frontCompWithZore(num, String.valueOf(count).length()));

        /*
            数据前不足补0
            格式说明符以%开头
            0为补位数, 6输出字符串格式,不足补0
         */
        System.out.println(String.format("%06d,%03d", 4, 4));

        //时间 (yyyy-MM-dd)
        System.out.println(String.format("%tF", new Date()));


    }


    @Test
    public void test_02() {
        //String uuid = UUID.randomUUID().toString();
        String uuid = "20190416/15553849471209865";

        String privateKey = EncKeyUtil.getPrivateKey(uuid);
        String publicKey = EncKeyUtil.getPublicKey(uuid);

        System.out.println("uuid: " + uuid);
        System.out.println("privateKey: " + privateKey);
        System.out.println("publicKey: " + publicKey);
    }

    @Test
    public void test_03() {
        String q = "ce1e04ad971cff2d820f992007d61d2c0ec290824b8cb5b811f0c7d939f02ba2";
        System.out.println(q.length());
    }

    /**
     * 　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     * 　　* @param sourceDate
     * 　　* @param formatLength
     * 　　* @return 重组后的数据
     *
     */

    public static String frontCompWithZore(int sourceDate, int formatLength) {

        /*
         * 0 指前面补充零
         * formatLength 字符总长度为 formatLength
         * d 代表为正数。
         */

        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

    @Test
    public void test_01() {
        int cal = cal(2);
        System.out.println(cal);
    }

    int cal(int n) {
        int sum = 0;
        int i = 1;
        int j = 1;
        for (; i <= n; ++i) {
            j = 1;
            for (; j <= n; ++j) {
                sum = sum + j;
            }
        }
        return sum;
    }


    @Test
    public void test04() {
        Map<String, String> map = new HashMap<>();
        /*System.out.println(1 << 4);
        System.out.println(1 << 30);
        System.out.println(Integer.MAX_VALUE);*/

        String s = "q";
        System.out.println(hash(s));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    @Test
    public void test05() {
        int num = 113;
        System.out.println(toBinary(65));
        System.out.println(num ^ (num >>> 5));
    }

    static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }

    @Test
    public void test06() {
        int num = 0;

        System.out.println(tableSizeFor(num));
    }

    static final int tableSizeFor(int cap) {
        int n = cap;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }

    @Test
    public void test07() {
        int i = 1;
        int n = 6;
        while (i <= n) {
            i = i * 2;
            System.out.println(i);
        }

        String s = String.valueOf(System.currentTimeMillis()) + new Random().nextInt(Integer.MAX_VALUE);
        System.out.println(s);

    }

    @Test
    public void test08() {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.dir"));
    }


    @Test
    public void test09() throws Exception {
        try {
            // int a = 1 / 0;
            throw new NullPointerException("null 999");
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("000");
            throw new ArithmeticException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("111");
            throw new Exception(e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("222");
            throwable.printStackTrace();
        }
    }

    @Test
    public void test10() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 1558056748000L;
        Date date = new Date(time);
        String dataFormat = sdf.format(date);
        System.out.println(dataFormat);
    }


    @Test
    public void test11() {
        Unsafe unsafe = getUnsafe();
        System.out.println("2323");
    }

    public static Unsafe getUnsafe() {

        Field f = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Student> list = new ArrayList<>();

    @Before
    public void init() {
        Student stuA = new Student(1, "A", "M", 184);
        Student stuB = new Student(2, "B", "G", 163);
        Student stuC = new Student(3, "C", "M", 175);
        Student stuD = new Student(4, "D", "G", 158);
        Student stuE = new Student(5, "E", "M", 170);
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);
    }

    @Test
    public void test12() {
        List<Student> m = list.stream().filter(s -> s.getSex().equals("M")).collect(Collectors.toList());
        for (Student s : m) {
            System.out.println(s.toString());
        }

        long count = list.stream().filter(s -> s.getHeight() >= 163).count();
        System.out.println(count);
    }


    @Data
    @AllArgsConstructor
    @ToString
    class Student {
        int no;
        String name;
        String sex;
        float height;
    }


    @Test
    public void test13() {
        File file = new File(".");
        System.out.println(file.getAbsoluteFile());
        File[] files = file.listFiles(File::isHidden);
        // File[] files = file.listFiles(f -> !f.isHidden());
        Arrays.asList(files).forEach(f -> System.out.println(f.getName()));
    }

    @Test
    public void test14() {
        int n = 7;
        int h = 3;
        System.out.println((n - 1) & h);
        System.out.println(Integer.toBinaryString(2));
        System.out.println(~3);
    }


    @Test
    public void test15() throws JsonProcessingException {
        List<SignFile> signFiles = new ArrayList<>();
        SignFile s1 = new SignFile("123456", "协议书1");
        SignFile s2 = new SignFile("654321", "协议书2");
        SignFile s3 = new SignFile("654321", "协议书2");
        signFiles.add(s1);
        signFiles.add(s2);
        signFiles.add(s3);

        long count1 = signFiles.stream().filter(s -> "123456".equals(s.getFileCode())).count();
        long count2 = signFiles.stream().filter(s -> "654321".equals(s.getFileCode())).count();

        System.out.println("count1: " + count1);
        System.out.println("count2: " + count2);

        RespData respData = RespData.buildSuccess(signFiles);

        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(respData);
        System.out.println(value);
    }

    @Test
    public void test16() throws JsonProcessingException {
        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("type", i + "");
            map.put("image", "078979879878");
            list.add(map);
        }
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(list);
        System.out.println(value);
    }


    // sz000050link3622
    // sz000050link3622
    @Test
    public void test17() {
        String userCode = "sz000050";
        String idNumber = "36222719961025151X";
        String s = (userCode + "link" + idNumber).substring(0, 16);
        System.out.println(s);
    }

    //172.16.1.253:8076 --> 192.168.1.76:8080
    //172.16.1.253:8077 --> 192.168.1.77:8080
    //172.16.1.253:8078 --> 192.168.1.78:8080
    private String host = "172.16.1.253:8076/8077/8078";

    //
    //192.168.1.77:8080
    //192.168.1.78:8080
    private String tager = "192.168.1.76/1.77/1.78:8080";

    private String callBackUrl_1 = "http://172.16.1.253:8076/xxxxx.do";
    private String callBackUrl_2 = "http://172.16.1.253:8077/xxxxx.do";
    private String callBackUrl_3 = "http://172.16.1.253:8078/xxxxx.do";

    private static Map<String, String> hostMap = new HashMap<String, String>() {
        {
            put("172.16.1.253:8076", "192.168.1.76:8080");
            put("172.16.1.253:8077", "192.168.1.77:8080");
            put("172.16.1.253:8078", "192.168.1.78:8080");
        }
    };

    @Test
    public void test18() {
        String callBackUrl = callBackUrl_3;
        for (String host : hostMap.keySet()) {
            if (callBackUrl.contains(host)) {
                System.out.println("原路径:" + callBackUrl);
                callBackUrl = callBackUrl.replace(host, hostMap.get(host));
                System.out.println("替换路径:" + callBackUrl);
                break;
            }
        }
    }

    @Test
    public void test19() {
        String idCard = "36222719911025151X";
        boolean validCard = IdcardUtil.isValidCard(idCard);
        System.out.println(validCard);
        long num = 36222719911025151L;

        System.out.println(num % 11);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 1568952838L;
        Date date = new Date(time * 1000);
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    public void test20() {
        /*String srcPath = "E:\\upload\\sz000064_0.jpg";
        String target = "E:\\upload\\tmp\\sz000064_0.jpg";
        String srcBase64 = Base64Util.fileToBase64(srcPath);
        System.out.println("srcBase64:" + srcBase64);
        ImageUtil.convert(srcPath, "jpg", target);
        String targetBase64 = Base64Util.fileToBase64(target);
        System.out.println("targetBase64:" + targetBase64);*/
        String imageType = "jpg2";
        if (!("PNG".equalsIgnoreCase(imageType) || "JPG".equalsIgnoreCase(imageType))) {
            System.out.println("if");
        }
        System.out.println("...");
    }

    @Test
    public void test21() {
        String data = "QY_20191119_DATA";
        //String data = "QY_20191021_DATA";
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(data);
        System.out.println(digestHex);
        System.out.println(digestHex.length());

        char[] chars = digestHex.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int index = i + 1;
            if (9 == index || 13 == index || 17 == index || 21 == index) {
                sb.append("-");
            }
            sb.append(chars[i]);
        }
        String result = sb.toString();
        System.out.println(result);

        String aa = "f6b2d1a2-ccb1-a4ed-568f-87ef3e11d0fa";
        System.out.println(aa.equals(result));
    }

    @Test
    public void test22() throws IOException, DataFormatException {

        String fileName = "9ecbba86-3b24-b7d5-8d36-52237a92dda0";
        String zipPath = "D:/" + fileName + ".zip";
        long currentTimeMillis = System.currentTimeMillis();
        String target = "D:/zipPath" + File.separator + currentTimeMillis + ".zip";
        /*try {
            AESDecrypter aes = new AESDecrypter();
            AesZipFileDecrypter zipFileDecrypter = new AesZipFileDecrypter(new File(zipPath), );
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*AESDecrypterBC aesDecrypterBC = new AESDecrypterBC();
        AesZipFileDecrypter zipFile = new AesZipFileDecrypter( new File(zipPath), aesDecrypterBC);
        AesZipFileDecrypter.charset = "utf-8";
        *//*ExtZipEntry entry = zipFile.getEntry( "zipSpecification.txt" );
        zipFile.extractEntry( entry, new File("doc/zipSpecification.txt"), "foo" );*//*
        List<ExtZipEntry> entryList = zipFile.getEntryList();
        for (ExtZipEntry entry: entryList) {
            zipFile.extractEntry();
            String fileName = entry.getCentralDirectoryEntry().getFileName();
            System.out.println(fileName);
        }*/

        SecretKeySpec keySpec = new SecretKeySpec(DecryptionZipUtil.getUTF8Bytes("QIYEDATACHANGTMP"), "AES");
        IvParameterSpec iv = new IvParameterSpec(DecryptionZipUtil.getUTF8Bytes("QiYeDataChangTmp"));
        DecryptionZipUtil.decryptFile(keySpec, iv, new File(zipPath), new File(target));

        String unzipTarget = "D:/zipPath" + File.separator + currentTimeMillis;
        File unzip = ZipUtil.unzip(target, unzipTarget, Charset.forName("GBK"));
        File[] files = unzip.listFiles();
        for (File file : files) {
            System.out.println(file.getPath());

            ArrayList<String> list = DecryptionZipUtil.readCsv(file.getPath());
            System.out.println(JSON.toJSONString(list));
        }
    }

    @Test
    public void test23() {
        testZhaoBiaoXiangMu();
    }

    private static void testZhaoBiaoXiangMu() {
//参数
        String queryType = "1";
        String qiYeCode = "1007";
        String qiYeName = "";
        String caType = "SZCA";
        String checkCode = "dd9f68c7-8d9b-456f-9639-772e50bb61b5";
//http请求参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("queryType", queryType);
        params.put("qiYeCode", qiYeCode);
        params.put("qiYeName", qiYeName);
        params.put("caType", caType);
        params.put("checkCode", checkCode);

        try {
            /*String json = HttpUtil.post(
                    "http://10.1.128.1:8080/jy-zhaobiao/interact/queryQiYeInfo.do",
                    params);*/
            String json = HttpUtil.post("http://220.163.129.150:10080/jy-zhaobiao/interact/queryQiYeInfo.do", params);
            System.out.println(json);
            /*Document document = XmlUtil.parseXml(json);
            Element element = document.getDocumentElement();*/
            //parse4Dom(json);
            org.dom4j.Document document = DocumentHelper.parseText(json);
            org.dom4j.Element root = document.getRootElement();
            org.dom4j.Element qiYeInfoList = root.element("QiYeInfoList");
            Iterator iter = qiYeInfoList.elementIterator();
            //Iterator iter = root.elementIterator("QiYeInfoList");
            while (iter.hasNext()) {
                org.dom4j.Element ele = (org.dom4j.Element) iter.next();
                String qiYe_code = ele.elementText("QiYe_Code");
                String qiYe_name = ele.elementText("QiYe_Name");
                String faRen_name = ele.elementText("FaRen_Name");
                String faRen_shenFenZheng_bh = ele.elementText("FaRen_ShenFenZheng_BH");
                String faRen_phone = ele.elementText("FaRen_Phone");
                String faRen_mobile = ele.elementText("FaRen_Mobile");
                String lianXiRen_name = ele.elementText("LianXiRen_Name");
                String lianXiRen_phone = ele.elementText("LianXiRen_Phone");
                String lianXiRen_mobile = ele.elementText("LianXiRen_Mobile");

                System.out.println(qiYe_code);
                System.out.println(qiYe_name);
                System.out.println(faRen_name);
                System.out.println(faRen_shenFenZheng_bh);
                System.out.println(faRen_phone);
                System.out.println(faRen_mobile);
                System.out.println(lianXiRen_name);
                System.out.println(lianXiRen_phone);
                System.out.println(lianXiRen_mobile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void parse4Dom(String xmlStr) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
        Element message = doc.getDocumentElement();
        NodeList list = message.getChildNodes();
        if (list != null) {
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                System.out.println("节点=" + node.getNodeName() + "\ttext="
                        + node.getFirstChild().getNodeValue());
            }
        }
    }


    @Test
    public void test24() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime start = LocalDateTime.of(2019, 12, 6, 14, 43);
        System.out.println(start);
        LocalDateTime end = LocalDateTime.of(2019, 12, 6, 15, 00);
        System.out.println(end);

        System.out.println(now.isAfter(start));
        System.out.println(now.isAfter(end));
    }

    @Test
    public void test25() {
        String str = "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";
        System.out.println(str.length());
    }


    @Test
    public void test26() {
        Defaulable defaulableImpl = DefaulableFactory.create(DefaulableImpl::new);
        System.out.println(defaulableImpl.notRequired());

        System.out.println("--------------------");

        Defaulable overridableImpl = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(overridableImpl.notRequired());
    }

    @Test
    public void test27() {
        System.out.println(null == null);
    }

    @Test
    public void test28() {

        int a = 0, b = 0;
        int n = 3;
        for (int i = 0; i < n; i++) {            // 循环次数为 n
            a++;
            for (int j = 0; j < n; j++) {        // 循环次数为 n
                b++;
                System.out.println("Hello, World!");      // 循环体时间复杂度为 O(1)
            }
        }
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }


    @Test
    public void test29() {
        int n = 8;
        int a = 0;
        for (int i = 2; i < n; i++) {
            System.out.println("i-->>" + i);
            a++;
            //i *= 2;
            i = 2 * i;
            System.out.println(String.format("i=%s", i));
        }
        System.out.println("a:" + a);
    }
    // 2^t < n

    @Test
    public void test30() {
        //String pattern = "^[\\u4E00-\\u9FA5A-Za-z]+$";
        //String pattern = "^[\\u4e00-\\u9fa5]{0,}$";
        //String pattern = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";
        /*String pattern = "[\\u4e00-\\u9fa5]";
        String content = "Fcd";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        boolean b = matcher.find();
        System.out.println(b);*/

        String u = "user_name= 朱晓梅";
        boolean contains = u.contains(" ");
        System.out.println(contains);
    }

    /**
     * MD5
     */
    @Test
    public void test31() {
        System.out.println(00001010 & 00010000);
    }

    @Test
    public void test32() {
        List<Car> carList_01 = new ArrayList<>();
        List<Car> carList_02 = new ArrayList<>();

        carList_01.add(new Car(1, "aa", 0));
        carList_01.add(new Car(2, "bb", 0));
        carList_01.add(new Car(3, "cc", 5));
        carList_01.add(new Car(4, "dd", 0));

        carList_02.add(new Car(5, "aa", 1));
        carList_02.add(new Car(1, "ee", 9));
        carList_02.add(new Car(6, "cc", 1));
        carList_02.add(new Car(7, "ff", 2));


        List<Car> carList_03 = new ArrayList<>();
        for (Car c : carList_01) {
            List<Car> list1 = carList_02.stream().filter(c_02 -> c_02.getId().equals(c.getExternalId())).collect(Collectors.toList());
            List<Car> list2 = carList_02.stream().filter(c02 -> c02.getExternalId().equals(c.getId())).collect(Collectors.toList());
            //long count = carList_02.stream().filter(c_02 -> !c_02.getId().equals(c.getExternalId())).filter(c02 -> !c02.getExternalId().equals(c.getId())).count();
            if (list1.size() == 0 && list2.size() == 0) carList_03.add(c);
        }

        for (Car c: carList_03) {
            System.out.println(JSON.toJSONString(c));
        }
    }
   static Set<Integer> peerEurekaNodeUrls = new HashSet<Integer>(Arrays.asList(1 ,2 , 3, 4));
    @Test
    public void test33() {
        List<Integer> collection = new ArrayList();
        collection.add(1);
        collection.add(3);
        updatePeerEurekaNodes(collection);
    }

    protected void updatePeerEurekaNodes(List<Integer> newPeerUrls) {
        Set<Integer> toShutdown = new HashSet<>(peerEurekaNodeUrls);
        toShutdown.removeAll(newPeerUrls);
        newPeerUrls.add(5);
        Set<Integer> toAdd = new HashSet<>(newPeerUrls);
        toAdd.removeAll(peerEurekaNodeUrls);
        System.out.println("toShutdown >>> " + JSON.toJSONString(toShutdown));
        System.out.println("toAdd >>> " + JSON.toJSONString(toAdd));
    }
}
