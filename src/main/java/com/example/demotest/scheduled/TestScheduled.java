package com.example.demotest.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestScheduled {


    @Scheduled(cron = "*/3 * * * * ?")
    public void test() {
        log.error(Thread.currentThread().getName() + "开始了");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error(Thread.currentThread().getName() + "执行完了");
    }

}
