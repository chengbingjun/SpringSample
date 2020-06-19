package com.example.test;

import java.util.Arrays;

public class Test02 {
    public static void main(String[] args) {
        int[] ary = {1, 3, 5, 8, 9, 6, 7, 4, 2};
        for (int i = 0; i < ary.length - 1; i++) {
            for (int j = 0; j < ary.length -1- i; j++) {
                if(ary[j]>ary[j+1]){
                    int temp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ary));
    }

}
