package com.example.demotest.demo;

import com.example.demotest.annotation.HandlingTime;
import com.example.demotest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("ljt", "yyp");
        String yyp = map.get("ljt");

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("ljt", "yyp");

        System.out.println("testService: " + testService);
        return testService.running();
    }

    // @HandlingTime
    @RequestMapping("qqq")
    public String qqq(String q) {
        testService.test01(q);
        return "success";
    }

    @HandlingTime(value = "计算时间")
    @RequestMapping("www")
    public String www(String q) {
        String s = (String) null;
        System.out.println("s:" + s);
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
