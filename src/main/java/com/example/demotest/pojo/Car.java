package com.example.demotest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Car {
    private Integer id;
    private String name;
    private Integer externalId;
}
