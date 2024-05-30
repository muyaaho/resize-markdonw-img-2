package com.example.resizeimage2.dto;

import lombok.Getter;

public class Form {
    @Getter
    private String url;
    @Getter
    private int size;
    private String result;

    public Form(String url, int size) {
        this.url = url;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Form{" +
                "url='" + url + '\'' +
                ", size=" + size +
                '}';
    }



}
