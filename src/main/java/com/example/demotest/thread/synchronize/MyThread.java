package com.example.demotest.thread.synchronize;


/**
 * @author lxk on 2017/11/17
 */
public class MyThread implements Runnable {

    static int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new MyThread());
        //new新实例
        Thread t2 = new Thread(new MyThread());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }

}

