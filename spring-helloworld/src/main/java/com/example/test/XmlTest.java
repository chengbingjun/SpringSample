package com.example.test;

import com.example.entity.Request;
import com.example.entity.RequestBody;
import com.example.entity.RequestHead;
import com.example.entity.Response;
import com.example.utils.HttpClientUtil;
import com.example.utils.XMLUtils;

import java.util.HashMap;
import java.util.Map;

public class XmlTest {

    private static String url = "http://cnlyon.com:6688/117HiService.asmx/BizHandler";

    public static void main(String[] args) {
        //组装入参
        Request request = assembleParams();
        //javabean转xml
        String requestXML = XMLUtils.toXml(request);
        System.out.println("requestXML:"+requestXML);
        Map<String,String> param = new HashMap<String, String>();
        param.put("inParams",requestXML);
        //发送请求
        String responseXML = HttpClientUtil.doPost(url,param);
        System.out.println("responseXML:"+responseXML);
        //格式化回参
        responseXML = formatToXml(responseXML);
//        ResponseHead head = XMLUtils.parseElementObj(responseXML,"head", ResponseHead.class);
//        System.out.println(head);
        //xml转javabean
        Response response = XMLUtils.parseElementObj(responseXML,"response", Response.class);
        System.out.println(response);

    }

    //参数组装
    public static Request assembleParams(){
        //入参head部分
        RequestHead requestHead = new RequestHead();
        requestHead.setBizcode("TY001");
        requestHead.setPartner("JIANDA");
        requestHead.setDeviceid("ZZJ010");
        requestHead.setDeviceid("1c958eca5cdc9e0f");
        requestHead.setIpaddress("172.0.0.1");
        //入参body部分
        RequestBody requestBody = new RequestBody();
        requestBody.setClinictype("普通门诊");
        requestBody.setHospcode("J");
        requestBody.setVisitdate("2019-11-01");
        //整个入参
        Request request = new Request();
        request.setHead(requestHead);
        request.setBody(requestBody);
        return request;
    }

    //xml格式化
    public static  String formatToXml(String xml){
        xml = xml.replace("&lt;","<");
        xml = xml .replace("&gt;",">");
        xml = xml.replaceAll("\\s*", "");
        System.out.println(xml);
        return xml;
    }
}
