package com.cbj.example.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author cbjun
 * @create 2021/2/25 11:19
 */
public class ThreadTest{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread01 thread01 = new Thread01();
        thread01.start();
        Thread thread02 = new Thread(new Thread02());
        thread02.start();
        FutureTask<String> task = new FutureTask<>(new Thread03());
        Thread thread03 = new Thread(task);
        thread03.start();
        System.out.println(task.get());
    }
}

class Thread01 extends Thread{
    @Override
    public void run() {
        System.out.println("线程1启动完毕");
    }
}

class Thread02 implements Runnable{

    @Override
    public void run() {
        System.out.println("线程2启动完毕");
    }
}

class Thread03 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("线程3启动完毕");
        return "成功";
    }
}