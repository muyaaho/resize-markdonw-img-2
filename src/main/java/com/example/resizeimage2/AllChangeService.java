package com.example.resizeimage2;

import com.example.resizeimage2.dto.Form;

public class AllChangeService {

    private String outputString;
    private final String inputString;
    private final int size;

    public AllChangeService(Form form) {
        inputString = form.getUrl();
        size = form.getSize();
    }


}
