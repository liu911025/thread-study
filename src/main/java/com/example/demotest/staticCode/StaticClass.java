package com.example.demotest.staticCode;

public class StaticClass {

    public StaticClass() {
        System.out.println("Constructor");
    }

    static {
        System.out.println("Static Block ");
    }

    {
        System.out.println("Instance Block");
    }

    public static void staticMethod() {
        System.out.println("Static method");
    }

    public void objectMethod() {
        System.out.println("Object method");
    }

}
