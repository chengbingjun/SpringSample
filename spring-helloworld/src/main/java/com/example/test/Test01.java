package com.example.test;

//各种排序
public class Test01 {
    public static void main(String[] args) {
        int[] ary = {1,3,5,8,9,6,7,4,2};
        //冒泡排序
         mppx(ary);

        //选择排序
        //xzpx(ary);

        //直接插入排序
//        crpx(ary);

    }

    public static void mppx(int[] ary){
        for(int i = 1; i<ary.length;i++){
            for(int j = 0;j<ary.length - i;j++){
                if(ary[j]>ary[j+1]){
                    int temp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = temp;
                }
            }

            System.out.print("第"+(i)+"轮：");
            for(int value:ary){
                System.out.print(value+" ");
            }
            System.out.println();
        }

        System.out.print("冒泡排序："+" ");
        for(int value:ary){
            System.out.print(value+" ");
        }
        System.out.println();
    }

    public static void xzpx(int ary[]){
        for(int i = 0;i<ary.length-1;i++){
            for(int j = i+1;j<ary.length;j++){
                if(ary[i] > ary[j]){
                    int temp = ary[i];
                    ary[i] = ary[j];
                    ary[j] = temp;
                }
            }
            System.out.print("第"+(i+1)+"轮：");
            for(int value:ary){
                System.out.print(value+" ");
            }
            System.out.println();
        }
        System.out.print("选择排序："+" ");
        for(int value:ary){
            System.out.print(value+" ");
        }
        System.out.println();
    }

    public static void crpx(int[] ary){
        int j ;
        for(int i = 1;i<ary.length;i++){
            int temp = ary[i];
            j = i;
            while(j>0 && temp<ary[j-1]){
                ary[j] = ary[j-1];
                j--;
            }
            ary[j] = temp;
        }
        System.out.print("插入排序："+" ");
        for(int value:ary){
            System.out.print(value+" ");
        }
        System.out.println();
    }
}
