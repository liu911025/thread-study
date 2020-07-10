package com.example.demotest.service;

public interface Defaulable {

    default String notRequired() {
        return "Default implementation";
    }

}
