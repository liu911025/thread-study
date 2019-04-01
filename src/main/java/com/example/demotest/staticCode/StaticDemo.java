package com.example.demotest.staticCode;

public class StaticDemo {

    public static void main(String[] args) {
        Sintelon s1 = new Sintelon();
        s1.set("q", "qqq");

        Sintelon s2 = new Sintelon();
        Object q = s2.get("q");
        System.out.println(q);
    }

}
