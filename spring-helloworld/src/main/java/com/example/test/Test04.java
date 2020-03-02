package com.example.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cbjun
 * @create 2019/11/28 9:32
 */
public class Test04 {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("3",5);
        System.out.println(map.get("1"));
    }
}
