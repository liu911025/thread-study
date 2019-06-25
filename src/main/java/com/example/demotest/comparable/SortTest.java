package com.example.demotest.comparable;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparator排序
 */
public class SortTest {

    @AllArgsConstructor
    @ToString
    class Dog{
        public int age;
        public String name;
    }


    public static void main(String[] args) {
        List<Dog> list= new ArrayList<>();
        list.add(new SortTest().new Dog(5, "DogA"));
        list.add(new SortTest().new Dog(6, "DogB"));
        list.add(new SortTest().new Dog(7, "DogC"));

        Collections.sort(list, (o1, o2) ->  o2.age - o1.age);

        System.out.println("给狗狗按照年龄倒序："+list);

        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println("给狗狗按名字字母顺序排序："+list);
    }

}
