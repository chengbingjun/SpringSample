package com.example.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        System.out.println(test01(1));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
//        String a = sdf.format(Calendar.getInstance().getTime());
//        String a = "123-abc";
//        String b = a.split("-")[0];
//        System.out.println(b);
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        //integerList.add(4);
        integerList.subList(0,4);

    }

    public static String test(int a) throws Exception {
        if (a == 1) {
            throw new Exception("错了");
        }
        return "666";
    }

    public static String test01(int a) {
        try {
            test(a);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }
}
