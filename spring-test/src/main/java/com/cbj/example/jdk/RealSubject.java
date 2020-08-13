package com.cbj.example.jdk;

public class RealSubject implements Subject {
    @Override
    public void sing() {
        System.out.println("RealSubject do 唱歌");
    }

    @Override
    public void dance() {
        System.out.println("RealSubjece do 跳舞");
    }
}
