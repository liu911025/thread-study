package com.example.demotest.nullable;

public class BookTest {

    public static void main(String[] args) {

        Book book = BookService.getBook(-1);
        if (book.isNull()) {
            System.out.println("not found");
        } else {
            System.out.println(book.toString());
        }
    }

}
