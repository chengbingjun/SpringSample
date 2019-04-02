package com.example.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler {
    private Object target;

    public JDKDynamicProxy(Object target){
        this.target = target;
    }

    //ClassLoader loader用来指明生成代理对象使用哪个类装载器，Class<?>[] interfaces用来指明生成哪个对象的代理对象，通过接口指定，InvocationHandler h用来指明产生的这个代理对象要做什么事情
    public <T> T getProxy(){
        //实例.getClass().getInterfaces()：获取该对象所有实现的接口
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //参数 proxy 指代理类，method表示被代理的方法，args为 method 中的参数数组，返回值Object为代理实例的方法调用返回的值。这个抽象方法在代理类中动态实现
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(method.getName().equals("sing")){
            System.out.println("唱歌前");
            result = method.invoke(target,args);
            System.out.println("唱歌后");
        }else{
            System.out.println("跳舞前");
            result = method.invoke(target,args);
            System.out.println("跳舞后");
        }
       return result;
    }
}
