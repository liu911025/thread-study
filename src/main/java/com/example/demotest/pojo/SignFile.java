package com.example.demotest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SignFile implements Serializable {

    private String fileCode;

    private String fileName;

}
