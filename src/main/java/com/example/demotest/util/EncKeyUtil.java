package com.example.demotest.util;

/**
 * Created by Cetacean on 2019/4/10 12:31
 *
 * 密钥生成的工具类
 *
 * 使用des对证书的uuid进行加密
 *    加密后的字符串截取为两部分
 *      一部分返回给用户保存,另一部分由系统保存在用户表内
 */
public class EncKeyUtil {

    /**
     * 加密密钥
     */
    private static final String DES_KEY = "cn.linksign.linkhealthy";

    /**
     * 开始截取为公钥的起始索引
     */
    private static final int BEGIN_INDEX = 10;

    /**
     * 获得公钥
     * @param uuid 证书的uuid
     * @return
     */
    public static String getPublicKey(String uuid){
        if (uuid == null || "".equals(uuid)){
            return "";
        }
        return DesUtils.encode(uuid,DES_KEY).substring(BEGIN_INDEX);
    }


    /**
     * 获取私钥
     * @param uuid 证书的uuid
     * @return
     */
    public static String getPrivateKey(String uuid){
        if (uuid == null || "".equals(uuid)){
            return "";
        }
        return DesUtils.encode(uuid,DES_KEY).substring(0,BEGIN_INDEX);
    }

    /**
     * 校验密钥正确性
     * @param publicKey
     * @param privateKey
     * @param uuid
     * @return
     */
    public static boolean checkEncKey(String privateKey,String publicKey,String uuid){
        if (uuid == null || "".equals(uuid)){
            return false;
        }
        return DesUtils.decode(String.format("%s%s", privateKey, publicKey),DES_KEY).equals(uuid);
    }

}
