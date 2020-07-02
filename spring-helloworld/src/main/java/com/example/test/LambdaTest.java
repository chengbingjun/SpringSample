package com.example.test;

import com.example.entity.PmsTask;
import com.google.common.collect.Lists;

import java.util.Comparator;
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
            if (e.length() > 2) {
                stringList.add(e);
            }
        });
        System.out.println(stringList.size());
        stringList.clear();
        System.out.println(stringList.size());

        //双冒号的用法：实例对象调用了add方法
        strings.forEach(stringList::add);
        PmsTask pmsTask = new PmsTask();
//        Supplier<Date> function = pmsTask::getCreateDate;
//        System.out.println(function.get());
//        比如表达式 person -> person.getAge(); 传入参数是 person，返回值是 person.getAge() ，那么方法引用Person::getAge 就对应着 Function<Person,Integer> 类型。
//        Function<PmsTask,Date> function = PmsTask::getCreateDate;
//        //等价于
//        Function<PmsTask,Date> function1 = pmsTask1 -> {
//            return pmsTask.getCreateDate();
//        };

        System.out.println(stringList.size());

        /**
         * Lambda 表达式需要 “函数式接口” 的支持
         * 函数式接口 : 接口中只有一个抽象方法的接口，称为函数式接口，可以通过 Lambda 表达式来创建该接口的对象
         * (若 Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明) 可
         * 以使用注解 @FunctionalInterface 修饰可以检查是否是函数式接口，
         */
        Runnable runnable1 = () -> {
            //测试着玩的 start->
            int i = 1 + 1;
            // <-end
            System.out.println("线程一启动" + i);
        };
        Thread thread1 = new Thread(runnable1);
        thread1.start();


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                //相当于
                int i = 1 + 1;
                System.out.println("线程二启动" + i);
            }
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        List<Integer> listA = Lists.newArrayList();
        listA.add(1);
        listA.add(2);
        listA.add(0);
        listA.add(6);
        listA.add(5);

        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        Comparator<Integer> comparator2 = (o1, o2) -> {
            return o1.compareTo(o2);
        };

        listA.sort(comparator2);
        listA.forEach(e -> {
            System.out.println(e);
        });
    }
}
