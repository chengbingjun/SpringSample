package com.cbj.example.jdk;

public class Test01 {
    public static void main(String[] args) {
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.sing();
        subject.dance();
    }
}
