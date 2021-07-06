package com.example.demotest.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    /**
     * @param text明文
     * @param key密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text, String key) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:" + md5str);
        return md5str;
    }

    // 不带秘钥加密
    public static String md52(String text) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text);
        System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());
        return md5str;
    }

    /**
     * MD5验证方法
     *
     * @param text明文
     * @param key密钥
     * @param md5密文
     */
    // 根据传入的密钥进行验证
    public static boolean verify(String text, String key, String md5) throws Exception {
        String md5str = md5(text, key);
        if (md5str.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        String text = "{" +
                "    \"PT_Id\":\"80\",\n" +
                "    \"ChangShang_Type_Id\":\"4\",\n" +
                "    \"Validate_String\":\"8FF21362-BABB-45B7-9260-A6EF89E29152\",\n" +
                "    \"Instruct\":\"getApplyOrderList\",\n" +
                "    \"Request_Obj\":{\n" +
                "        \"Current_Page\":\"1\",\n" +
                "        \"Page_Size\":\"15\"\n" +
                "    }\n" +
                "}";
        String key = "C6F17EC9-32E8-44CC-BE54-AE1426272619";
        String md5str = DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:" + md5str);
    }

}
