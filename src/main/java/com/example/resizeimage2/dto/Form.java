package com.example.resizeimage2.dto;

public class Form {
    private String url;
    private int size;

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
