package com.example.demotest.staticCode;

import java.util.HashMap;
import java.util.Map;

public class Sintelon {

    static class TestMap{
        public static Map<String, Object> map = new HashMap<>();
    }

    public void set(String key, Object obj) {
        TestMap.map.put(key, obj);
    }

    public Object get(String key) {
        return TestMap.map.get(key);
    }
}