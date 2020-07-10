package com.example.demotest.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;

@JacksonXmlRootElement(localName = "signFile")
@Data
//@NoArgsConstructor
public class SignFile implements Serializable {

    @JacksonXmlProperty(localName = "fileCode")
    private String fileCode;

    @JacksonXmlProperty(localName = "fileName")
    private String fileName;

    @JacksonXmlProperty(localName = "positions")
    //private List<Position> positions;
    private Position positions;

    @Tolerate
    public SignFile() {
    }

    public SignFile(String fileCode, String fileName) {
        this.fileCode = fileCode;
        this.fileName = fileName;
    }
}
