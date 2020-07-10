package com.example.demotest.service.impl;

import com.example.demotest.service.Defaulable;

public class OverridableImpl implements Defaulable {

    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}
