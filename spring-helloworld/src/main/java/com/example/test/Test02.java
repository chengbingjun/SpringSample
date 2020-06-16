package com.example.test;

public class Test02 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soapenv:Envelope "
                + "    xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
                + " xmlns:q0='http://WebXml.com.cn/' "
                + "    xmlns:xsd='http://www.w3.org/2001/XMLSchema' "
                + " xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' >");
        sb.append("<soapenv:Body>");
        sb.append("<q0:getWeatherbyCityName>");
        sb.append("<q0:theCityName>唐山</q0:theCityName> ");
        sb.append("</q0:getWeatherbyCityName>");
        sb.append("</soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        System.out.println(sb.toString());
        System.out.println("aaa");
    }

}
