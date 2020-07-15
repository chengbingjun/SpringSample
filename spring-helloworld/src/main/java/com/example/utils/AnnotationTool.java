package com.example.utils;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTool {

    /**
     * 类中方法上的注解
     *
     * @param c          注解所使用所在的类
     * @param annotation 注解的类
     * @return 字典属性列表
     */
    public static <T, K extends Annotation> K getAnnotation(Class<T> c, Class<K> annotation, String methodName) {
        if (c == null) {
            return null;
        }
        K k = null;
        try {
            Method testMethod = null;
            //获取类中所有方法
            Method[] methods = c.getMethods();
            //匹配到想要的方法(根据名字匹配)，不过方法的重载会出现问题
            for (Method method : methods) {
                if ((method.getName()).equals(methodName)) {
                    testMethod = method;
                    break;
                }
            }
            //取得方法上的@ApiOperation注解
            k = testMethod.getAnnotation(annotation);
            //拿到该注解的value属性
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

}
