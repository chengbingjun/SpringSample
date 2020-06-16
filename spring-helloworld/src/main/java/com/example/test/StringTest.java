package com.example.test;

/**
 * @Author cbjun
 * @create 2019/11/28 9:32
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = "abcd";
        String str8 = "abcd";
        String str2 = new String("abcd");
        String str7 = new String("abcd");
        String str3 = "ab";
        String str4 = "cd";
        /**
         * str5这个过程相当于str5 = new StringBuffer().append(str3).append(str4).toString();
         * 其实就是新创建了一个String对象
         */
        String str5 = str3 + str4;
        String str6 = new String(str3 + str4);
        System.out.println(str1 == str2);//false
        System.out.println(str1 == str5);//false
        System.out.println(str1 == str6);//false
        System.out.println(str2 == str6);//false
        System.out.println(str2 == str7);//false
        System.out.println(str1 == str8);//true
    }
}
