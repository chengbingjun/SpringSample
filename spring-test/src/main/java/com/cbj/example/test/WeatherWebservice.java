package com.cbj.example.test;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//天气相关webservice调用，其中入参格式需要通过SoapUI去解析获取。
//生成客户端方式请求在项目：springboot中
public class WeatherWebservice {
    public static void main(String[] args) throws Exception {
        String str = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getWeatherbyCityName>\n" +
                "         <web:theCityName>杭州</web:theCityName>\n" +
                "      </web:getWeatherbyCityName>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String response = doPostByWebservice(str, "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl", "");
        System.out.println(response);
    }

    public static String doPostByWebservice(String accInfoXml, String url, String targetNamespace)
            throws Exception {
        PostMethod postMethod = new PostMethod(url);
        // 然后把Soap请求数据添加到PostMethod中
        byte[] b = accInfoXml.getBytes("utf-8");
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                "text/xml; charset=utf-8");
        postMethod.setRequestEntity(re);

        // 最后生成一个HttpClient对象，并发出postMethod请求
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == 200) {
            System.out.println("调用成功！");
            String soapResponseData = postMethod.getResponseBodyAsString();
            System.out.println(soapResponseData);
        } else {
            System.out.println("调用失败！错误码：" + statusCode);
        }
        return null;
    }

}
