package com.cbj.example.test;

import com.cbj.example.utils.HttpClientUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HttpTest {

    public static void main(String[] args) throws IOException {
        String url = "http://www.baidu.com";
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
        String file = "D:\\baidu.html";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(result.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
