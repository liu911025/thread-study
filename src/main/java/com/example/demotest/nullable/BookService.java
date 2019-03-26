package com.example.demotest.nullable;

public class BookService {

    public static Book getBook(int id) {
        if (id < 0) {
            return new NullBook();
        }
        return new Book(id, id + "-book", 22.0);
    }

}
