package com.example.demotest.demo;

import com.example.demotest.annotation.HandlingTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @HandlingTime
    @RequestMapping("qqq")
    public String qqq(String q) {
        try {
            if ("q".equals(q)) {
                throw new NullPointerException("eqwqwq");
            }
            return "success";
        }catch (Exception e) {
            System.out.println("最大异常处理!");
        }
        return "fail";
    }

    @HandlingTime
    @RequestMapping("www")
    public String www(String q) {
        int i = 100;
        while (i > 0) {
            i--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "www";
    }

}
