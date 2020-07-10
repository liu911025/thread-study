package com.example.demotest.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "position")
@Data
public class Position implements Serializable {

    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(localName = "fileCode")
    private String fileCode;

    @JacksonXmlProperty(localName = "keyword")
    private String keyword;

}
