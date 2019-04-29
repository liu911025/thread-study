package com.example.demotest.exceptionHanld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Map<String, String> globalException(Exception e) {

        e.printStackTrace();

        if (e instanceof NullPointerException) {
            log.info(e.getMessage());
        }

        Map<String, String> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

}
