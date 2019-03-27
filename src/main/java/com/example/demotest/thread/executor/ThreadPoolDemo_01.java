package com.example.demotest.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 结果只有9个, 100 / 0 会报错
 * 如果使用submit无法显示错误信息，一种方式是放弃使用submit，另一种是可以使用execute
 *
 * 使用submit 错误的堆栈信息跑出来的时候会被内部捕获到，所以打印不出来具体的信息
 */
public class ThreadPoolDemo_01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int index = i;
            //executorService.submit(() -> divTask(100, index));
            executorService.execute(() -> divTask(100, index));
        }
        executorService.shutdown();
    }


    private static void divTask(int a, int b) {
        double result = a / b;
        System.out.println(result);
    }

}
