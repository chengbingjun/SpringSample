package com.cbj.example.test;

import java.util.Date;

/**
 * @Author cbjun
 * @create 2020/1/3 14:47
 */
public class DateTest {

    public static void main(String[] args) {
        Date date = new Date();
        //String formatDate = new SimpleDateFormat("yyyyMMddHHmmss").format(date)+".00";
        String formatDate = "200010000000";
        Double sort = Double.valueOf(formatDate);
        System.out.println(sort);
        Integer i = 128;
        System.out.println(i>0);
    }
}
