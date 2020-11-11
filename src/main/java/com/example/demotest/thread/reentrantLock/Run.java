package com.example.demotest.thread.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Run {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(() -> runMethod(lock, 0L), "t1").start();
        new Thread(() -> runMethod(lock, 5000L), "t2").start();
        new Thread(() -> runMethod(lock, 1000L), "t3").start();
        new Thread(() -> runMethod(lock, 5000L), "t4").start();
        new Thread(() -> runMethod(lock, 1000L), "t5").start();

    }


    private static void runMethod(Lock lock, Long sleepTime) {
        lock.lock();
        try {
            Thread.sleep(sleepTime);
            System.out.println("ThreadName:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
