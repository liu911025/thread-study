package com.example.demotest.exceptionHanld;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Map<String, String> globalException(Exception e) {
        Map<String, String> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

}
