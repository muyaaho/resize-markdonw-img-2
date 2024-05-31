package com.example.resizeimage2;

import com.example.resizeimage2.dto.Form;
import java.io.IOException;
import lombok.Getter;

@Getter
public class AllChangeService {

    private final String outputString;
    private final String inputString;
    private final int size;

    private final AllChangeTools allChangeTools = new AllChangeTools();

    public AllChangeService(Form form) throws IOException {
        inputString = form.getUrl();
        System.out.println("inputString = " + inputString);
        size = form.getSize();
        System.out.println("size = " + size);
        outputString = allChangeTools.changeLines(inputString, size);
        System.out.println("outputString = " + outputString);
    }
}
