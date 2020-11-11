package com.example.demotest.thread.countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SummonDragonDemo {

    private static int count = 7;

    private static final CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= count; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("i:" + index);
                    int sleepTime = new Random().nextInt(5000);
                    System.out.println("sleepTime:" + sleepTime);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }
        System.out.println("开始等待!");
        latch.await();
        System.out.println("等待完成!");
    }

}