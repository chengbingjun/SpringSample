package com.example.cglib;

public class Test01 {
    public static void main(String[] args) {
        HelloService helloService = new MyMethodInterceptor(new HelloService()).getProxy();
        helloService.sayHello();
        helloService.sayOther("666");
    }
}
