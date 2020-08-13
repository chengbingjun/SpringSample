package com.cbj.example.utils;

import com.cbj.example.entity.PmsTask;
import com.google.common.base.Joiner;

import java.text.Collator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @Author cbjun
 * @create 2020/6/30 15:26
 */
public class ListUtil {

    /**
     * 用于基本数据类型和一些特殊的引用数据类型（String等）
     * list集合的去重
     * 缺点：需要重写equals方法和hashCode方法
     *
     * @param list
     * @return
     */
    public static List listDistinct1(List list) {
        return (List) list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 用于引用数据类型
     * list集合去重
     * 缺点：会按照去重的属性进行排序
     *
     * @param list
     */
    public static List listDistinct2(List<PmsTask> list) {
        return list.stream().collect(collectingAndThen
                (toCollection(() -> new TreeSet<>(comparing(PmsTask::getID))), ArrayList::new));
    }

    /**
     * list集合去重（无需重写任何方法）
     * 缺点：但是需要额外写一个distinctByKey方法
     *
     * @param list
     */
    public static List listDistinct3(List<PmsTask> list) {
        return list.stream().filter(ListUtil.distinctByKey(PmsTask::getName)).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
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
     * 1.8新特性：list.stream()的相关方法
     */
    public static void streamFuc() {
        List<String> strings = new ArrayList<String>(Arrays.asList("xx", "yyyy", "z", "bbb", "kkkkkk"));
        //做筛选
        strings = strings.stream().filter(e -> e.length() > 2).collect(Collectors.toList());
        System.out.println(strings);
        //做排序
        strings = strings.stream().sorted((p1, p2) -> (p2.length() - p1.length())).collect(Collectors.toList());
        System.out.println(strings);
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PmsTask pmsTask1 = new PmsTask(1, "f号", f.parse("2019-01-01"));
            PmsTask pmsTask2 = new PmsTask(2, "e号", f.parse("2018-01-02"));
            PmsTask pmsTask3 = new PmsTask(3, "d号", f.parse("2018-01-03"));
            PmsTask pmsTask4 = new PmsTask(4, "c号", f.parse("2018-01-04"));
            PmsTask pmsTask5 = new PmsTask(5, "b号", f.parse("2017-01-06"));
            PmsTask pmsTask6 = new PmsTask(6, "a号", f.parse("2017-01-05"));
            PmsTask pmsTask7 = new PmsTask(6, "f号", f.parse("2020-01-01"));
            List<PmsTask> pmsTasks = new ArrayList<PmsTask>(Arrays.asList(pmsTask2, pmsTask3, pmsTask1, pmsTask6, pmsTask5, pmsTask4, pmsTask7));
            System.out.println(pmsTasks);
            //去重
            pmsTasks = listDistinct3(pmsTasks);
            System.out.println(pmsTasks);
            //根据拼音首字母排序
            pmsTasks = pmsTasks.stream().sorted((p1, p2) -> (Collator.getInstance(java.util.Locale.CHINA)).compare(p1.getName(), p2.getName())).collect(Collectors.toList());
            //根据日期排序(下面两行代码意思是一样的)
            // pmsTasks = pmsTasks.stream().sorted((p1, p2) -> p1.getCreateDate().compareTo(p2.getCreateDate())).collect(Collectors.toList());
            //比如表达式 person -> person.getAge(); 传入参数是 person，返回值是 person.getAge() ，那么方法引用Person::getAge 就对应着 Function<Person,Integer> 类型。
            pmsTasks = pmsTasks.stream().sorted(Comparator.comparing(PmsTask::getCreateDate)).collect(Collectors.toList());
            System.out.println(pmsTasks);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

}
