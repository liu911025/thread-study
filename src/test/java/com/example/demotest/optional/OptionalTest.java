package com.example.demotest.optional;

import com.example.demotest.nullable.Book;
import com.example.demotest.nullable.NullBook;
import com.example.demotest.nullable.Nullable;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<Book> empty = Optional.empty();
        empty.get();
    }

    /**
     * Optional.of(null) 执行时报错
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        Optional<Book> opt = Optional.of(null);
        Book book = opt.get();
    }

    /**
     * Optional.ofNullable(null) 执行不报错
     * opt.get() 报错
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional() {
        Optional<Book> opt = Optional.ofNullable(null);
        Book book = opt.get();
    }
}
