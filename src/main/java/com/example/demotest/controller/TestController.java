package com.example.demotest.controller;

import com.example.demotest.annotation.HandlingTime;
import com.example.demotest.pojo.SignFile;
import com.example.demotest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("#{'${people.age}'.split(',')}")
    private List<Integer> ageList = new ArrayList<>();

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


    @RequestMapping(value = "/signFile", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public SignFile creditLoanApplyNotify(@RequestBody SignFile req) {
        System.out.println(123);
        return req;

    }

    @RequestMapping("restTemplate")
    @ResponseBody
    public String restTemplate() {
        String url = "https://caservice.linksign.cn:9011/api/account/license";
        HttpEntity<String> reqEntity = new HttpEntity<>("123");
        String respResult = restTemplate.postForObject(url, reqEntity, String.class);
        return "123";
    }
}
