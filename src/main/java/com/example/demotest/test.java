package com.example.demotest;

import com.example.demotest.util.EncKeyUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
 　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
 　　* @param sourceDate
 　　* @param formatLength
 　　* @return 重组后的数据
 　　*/

    public static String frontCompWithZore(int sourceDate,int formatLength) {

    /*
     * 0 指前面补充零
     * formatLength 字符总长度为 formatLength
     * d 代表为正数。
     */

    String newString = String.format("%0"+formatLength+"d", sourceDate);
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
}
