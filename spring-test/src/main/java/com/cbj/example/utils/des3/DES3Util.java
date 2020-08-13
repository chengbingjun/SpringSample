package com.cbj.example.utils.des3;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class DES3Util {

    private static final String KEY_ALGORITHM = "DESede";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS7Padding";// 默认的加密算法


    /**
     * 字符串转16进制大写
     * @param str
     * @return
     * @throws Exception
     */
    public static String toHexString(String str) throws Exception {
        // 用于接收转换结果
        String hexString = "";
        // 1.校验是否包含特殊字符内容
        // java特殊转义符
        // String[] escapeArray = {"\b","\t","\n","\f","\r","\'","\"","\\"};
        String[] escapeArray = {"\b","\t","\n","\f","\r"};
        // 用于校验参数是否包含特殊转义符
        boolean flag = false;
        // 迭代
        for (String esacapeStr : escapeArray) {
            // 一真则真
            if (str.contains(esacapeStr)) {
                flag = true;
                break;// 终止循环
            }
        }
        // 包含特殊字符
        if (flag) throw new Exception("参数字符串不能包含转义字符！");

        // 16进制字符
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        // String--&gt;byte[]
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(hexArray[bit]);
            bit = bs[i] & 0x0f;
            sb.append(hexArray[bit]);
        }
        hexString = sb.toString();
        return hexString;
    }

    /**
     * DESede 加密操作
     *
     * @param content
     *            待加密内容
     * @param key
     *            加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return Base64.encodeBase64String(result);// 通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * } } DESede 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM); // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key)); // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     }
     * 生成加密秘钥
     *
     * @return
     */ private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的KeyGenerator 对象
        KeyGenerator kg = null; try { kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(new SecureRandom(key.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
            // 转换为DESede专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } return null;
    }

    public static void main(String[] args) {
        String content = "123456";
        String key = "7465737420537472696E67206B657931";
        System.out.println("content:" + content);
        String s1 = DES3Util.encrypt(content, key);
        System.out.println("加密:" + s1);
        System.out.println("解密:" + DES3Util.decrypt(s1, key));
    }


}

