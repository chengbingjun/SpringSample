package com.example.utils;

import com.example.entity.PmsTask;
import com.google.common.base.Joiner;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cbjun
 * @create 2020/6/30 15:26
 */
public class ListUtil {

    /**
     * list集合的去重
     *
     * @param list
     * @return
     */
    public static List listDistinct(List list) {
        return (List) list.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        List<String> list = Lists.newArrayList();
//        list.add("1");
//        list.add("1");
//        list.add("2");
//        list.add("2");
//        list = ListUtil.listDistinct(list);
//        list.forEach(e->{
//            System.out.println(e);
//        });
//
//        int i = 1;
//        System.out.println(ListUtil.dd(i));
//
//        System.out.println(stringToList("a,a,b,c"));
//
//        System.out.println(listToString(list));
        streamFuc();

    }

    /**
     * 随便定义的一个泛型方法
     *
     * @param a
     * @param <T>
     * @return
     */
    public static <T> String dd(T a) {
        T t = a;
        if (t instanceof String) {
            return "是一个String";
        } else if (t instanceof Integer) {
            Integer i = 2;
            return "是一个Integer";
        }
        return "其他类";
    }

    /**
     * String to List<String>
     *
     * @param string
     * @return
     */
    public static List<String> stringToList(String string) {
        String str[] = string.split(",");
        return Arrays.asList(str);
    }

    /**
     * List<String> to String
     *
     * @param strings
     * @return
     */
    public static String listToString(List<String> strings) {
        //on方法里面的参数：以什么符号将list里面的元素连接起来
        String str = Joiner.on("").join(strings);
        return str;
    }

    public static void streamFuc() {
        List<String> strings = new ArrayList<String>(Arrays.asList("xx", "yyyy", "z", "bbb", "kkkkkk"));
        //做筛选
        strings = strings.stream().filter(e -> e.length() > 2).collect(Collectors.toList());
        System.out.println(strings);
        //做排序
        strings = strings.stream().sorted((p1, p2) -> (p2.length() - p1.length())).collect(Collectors.toList());
        System.out.println(strings);

        PmsTask pmsTask1 = new PmsTask(1, "a号");
        PmsTask pmsTask2 = new PmsTask(2, "b号");
        PmsTask pmsTask3 = new PmsTask(3, "c号");
        PmsTask pmsTask4 = new PmsTask(4, "d号");
        PmsTask pmsTask5 = new PmsTask(5, "e号");
        PmsTask pmsTask6 = new PmsTask(6, "f号");
        List<PmsTask> pmsTasks = new ArrayList<PmsTask>(Arrays.asList(pmsTask2, pmsTask3, pmsTask1, pmsTask6, pmsTask5, pmsTask4));
        System.out.println(pmsTasks);
        //根据拼音首字母排序
        pmsTasks = pmsTasks.stream().sorted((p1, p2) -> (Collator.getInstance(java.util.Locale.CHINA)).compare(p1.getName(), p2.getName())).collect(Collectors.toList());
        System.out.println(pmsTasks);
    }

}
