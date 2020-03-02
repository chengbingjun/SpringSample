package com.example.test;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author cbjun
 * @create 2020/3/2 11:06
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<String> strings = Lists.newArrayList();
        strings.add("小宋");
        strings.add("就是个");
        strings.add("憨憨");
        List<String> stringList = Lists.newArrayList();
        strings.forEach(e -> {
            if(e.length()>2){
                stringList.add(0,e);
            }
        });
        System.out.println(stringList.size());

        Runnable runnable1 = () ->{
            System.out.println("线程一启动");
        };
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }
}
