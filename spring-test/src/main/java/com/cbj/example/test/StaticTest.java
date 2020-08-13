package com.cbj.example.test;

/**
 * @Author cbjun
 * @create 2020/5/25 10:17
 */

/**
 * 总结：
 *
 * ① 新建对象前要先加载类（静态语句）
 *
 * ② 加载子类之前要先加载父类
 *
 * ③ 执行构造器之前要先初始化成员变量
 *
 * ④ 新建子类之前要先执行父类的构造器
 */
public class StaticTest {
    Person person = new Person("WeatherWebservice");
    static{
        System.out.println("test static");
    }

    public StaticTest() {
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}


class MyClass extends StaticTest {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}

