package com.example.utils.sm2;


import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.*;


/**
 * @author: ggp
 * @Date: 2019/3/18 18:45
 * @Description:
 */
public class SM2Util {

    public static Signature signature;
    public static KeyPair keyPair;

    public static KeyPair getKeyPair() {
        return keyPair;
    }

    public static void setKeyPair(KeyPair keyPair) {
        SM2Util.keyPair = keyPair;
    }
    public static void createKeyPair(){
        // 获取SM2 椭圆曲线推荐参数
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造EC 算法参数
        ECNamedCurveParameterSpec sm2Spec = new ECNamedCurveParameterSpec(
                // 设置SM2 算法的 OID
                GMObjectIdentifiers.sm2p256v1.toString()
                // 设置曲线方程
                , ecParameters.getCurve()
                // 椭圆曲线G点
                , ecParameters.getG()
                // 大整数N
                , ecParameters.getN());
        try {
            // 创建 密钥对生成器
            KeyPairGenerator gen = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            // 使用SM2的算法区域初始化密钥生成器
            gen.initialize(sm2Spec, new SecureRandom());
            // 获取密钥对
            SM2Util.keyPair = gen.generateKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static {
        try {
            signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),new BouncyCastleProvider());
            System.out.println(GMObjectIdentifiers.sm2sign_with_sm3.toString());
            createKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 签名
     * @param privateKey 签名私钥
     * @param plainText 明文
     * @return
     */
    public static String encrypt(PrivateKey privateKey,String plainText){

        try {
            signature.initSign(privateKey);
            signature.update(plainText.getBytes());
            byte[] bytes = signature.sign();
            return new String(bytes,0,bytes.length,"ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证签名
     * @param publicKey 签名公钥
     * @param signResult 签名结果
     * @param plainText  明文
     * @return
     */
    public static boolean verify(PublicKey publicKey,byte[] signResult,String plainText){
        boolean result = false;
        try {
            signature.initVerify(publicKey);
            signature.update(plainText.getBytes());
            result = signature.verify(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从本地导入私钥
     *
     * @param path
     * @return
     */
    public static BigInteger importPrivateKey(String path) {
        File file = new File(path);
        try {
            if (!file.exists())
                return null;
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            BigInteger res = (BigInteger) (ois.readObject());
            ois.close();
            fis.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


