package com.cbj.example.test;

import java.util.Arrays;

/**
 * @Author cbjun
 * @create 2020/11/12 15:52
 */
//二分法又名折半查找(必须有序)
public class DivisionTest {

    public static void main(String[] args) {
        int[] ary = {1, 5, 71, 30, 0, 9, 10, 82, 95, 88};
        for (int i = 1; i < ary.length; i++) {
            for (int j = 0; j < ary.length - i; j++) {
                if (ary[j] > ary[j + 1]) {
                    int temp = ary[j];
                    ary[j] = ary[j + 1];
                    ary[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ary));
        int index = division(ary, 0, 0, ary.length - 1);
        System.out.println(index);
    }

    public static int division(int[] ary, int value, int begin, int end) {
        int mid = (begin + end) / 2;
        if (begin <= end) {
            if (ary[mid] == value) {
                return mid;
            } else if (ary[mid] > value) {
                return division(ary, value, begin, mid - 1);
            } else if (ary[mid] < value) {
                return division(ary, value, mid + 1, end);
            }
        }
        return -1;
    }
}
