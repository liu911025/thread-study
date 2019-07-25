package com.example.demotest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CallbackController {

    @RequestMapping("test")
    public void test(Map<String, String> map) {
        System.out.println("callback is success");
        System.out.println(map);
    }

}
