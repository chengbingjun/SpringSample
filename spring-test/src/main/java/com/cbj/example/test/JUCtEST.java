package com.cbj.example.test;

/**
 * @Author cbjun
 * @create 2021/3/25 15:46
 */
//控制线程交替输出
public class JUCtEST {

    static int num = 1;
    static Object lock = new Object();

    public static void main(String[] args) {

        Thread thread01 = new Thread(() -> {
            synchronized (lock) {
                while (num != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(1);
                num = num + 1;
                lock.notifyAll();
            }
        });
        Thread thread03 = new Thread(() -> {
            synchronized (lock) {
                while (num != 3) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(3);
                lock.notifyAll();
            }
        });
        Thread thread02 = new Thread(() -> {
            synchronized (lock) {
                while (num != 2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(2);
                num = num + 1;
                lock.notifyAll();
            }
        });

        thread02.start();
        thread03.start();
        thread01.start();

    }

}
