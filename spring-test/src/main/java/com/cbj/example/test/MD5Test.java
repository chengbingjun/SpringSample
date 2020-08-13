package com.cbj.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cbj.example.entity.SelOrderRequest;
import com.cbj.example.utils.HttpClientUtil;
import com.cbj.example.utils.des3.DES;
import com.cbj.example.utils.des3.DES3Util;
import com.cbj.example.utils.md5.MD5Util;

import java.util.Map;

public class MD5Test {

    private static String url = "http://183.131.120.15:5555/WorkService/do";
    private static String key = "123456";

    public static void main(String[] args) {
        try {
            //获取入参（json）
            String str  = toJson();;
            System.out.println(str);
            //入参格式化
            str = str.replace("\"","\'");
            str = "\""+str+"\"";
            System.out.println(str);
            //发送请求
            String result = HttpClientUtil.doPostJson(url,str);
            //回参转map
            Map<String,Object> map = JSON.parseObject(result,Map.class);
            System.out.println("map:"+map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toJson() throws Exception {
        //组装参数
        SelOrderRequest request = new SelOrderRequest();
        request.setFuncode("5001");
        String orderno = "1001";
        //关键字加密
        orderno = new String(DES.encrypt(orderno.getBytes(),"7465737420537472"));
        orderno = DES3Util.toHexString(orderno);
        System.out.println(orderno);
        request.setOrderno(orderno);
        request.setKey("funcode,orderno");
        request.setEnkey("orderno");
        request.setSafecontrol("DES");
        //获取签名
        String sign = MD5Encrypt("5001"+orderno+key);
        request.setSign(sign);
        String result = JSONObject.toJSONString(request);
//        String funCode = "5001";
//        String orderNo = "1001";
//        String key = "funCode,orderNo";
//        String sign = MD5Encrypt(funCode+orderNo+key);
//        String result1 = "{'funcode':'5001','key':'funcode,orderno','orderno':'1001','sign':'2FAE202FB871656719FDB638285D122208BD1BDB'}";
        return result;
    }

    public static String MD5Encrypt(String input){
        String md5Result = MD5Util.stringMD5(input,"MD5")
                .substring(0,32);
        System.out.println("md5:"+md5Result);
        String sha1Result = MD5Util.stringMD5(md5Result,"SHA1")
                .substring(0,40);
        System.out.println("sha1:"+sha1Result);
        return sha1Result;
    }
}
