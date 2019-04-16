package com.example.demotest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class test {

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode =
            new AtomicInteger(16);


    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.toArray();
        String[] strs = new String[list.size()];
        list.toArray(strs);
        System.out.println(strs.toString());*/

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

}
