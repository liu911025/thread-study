package com.example.demotest.service.impl;

import com.example.demotest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String running() {
        System.out.println("running...");
        return "running";
    }

    @Override
    public String test01(String q) {
        try {
            if ("q".equals(q)) {
                throw new NullPointerException("eqwqwq");
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("test01异常, 异常信息为: {}" + e.getMessage());
            throw new NullPointerException(e.getMessage());
        }
        return q;
    }
}
