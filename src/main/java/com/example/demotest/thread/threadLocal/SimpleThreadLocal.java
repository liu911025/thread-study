package com.example.demotest.thread.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleThreadLocal<T> {

    private static Map<Thread, Object> valueMap = Collections.synchronizedMap(new HashMap<>());

    public T set(T val) {
        Thread thread = Thread.currentThread();
        T t = (T) valueMap.get(thread);
        valueMap.put(thread, val);
        return t;
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T t = (T) valueMap.get(thread);
        if (t == null && !valueMap.containsKey(thread)) {
            t = initValue();
            valueMap.put(thread, t);
        }
        return t;
    }

    private T initValue() {
        return null;
    }

    public T remove() {
        T t = (T) valueMap.remove(Thread.currentThread());
        return t;
    }
}
