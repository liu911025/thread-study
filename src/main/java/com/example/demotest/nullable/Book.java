package com.example.demotest.nullable;

public class Book implements Nullable {

    private int id;
    private String name;
    private double price;

    public Book() {
    }

    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public static Book createNullBook() {
        return new NullBook();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
