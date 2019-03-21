package com.example.demotest;

public class test {


    public static void main(String[] args) {
        try {
            //int a = 1/0;
            Object o = new Object();
            o.wait();
        }catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("NPE");
        }finally {
            System.out.println("finally print!");
        }
    }

}
