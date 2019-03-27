package com.example.demotest.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.submit(() -> System.out.println("i:" + index +
                    " executorService"));
        }
        executorService.shutdown();
    }
}
