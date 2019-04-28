package com.example.demotest.demo;

import com.example.demotest.annotation.HandlingTime;
import com.example.demotest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * controller方法
     * @return
     */
    @RequestMapping("yyy")
    private String yyy() {
        System.out.println("testService: " + testService);
        return testService.running();
    }

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

    @RequestMapping("jsonp")
    @ResponseBody
    public String jsonp(@RequestParam("callback") String callback) {
        System.out.println("jsonp");
        return callback + "({'result':0})";
    }

}
