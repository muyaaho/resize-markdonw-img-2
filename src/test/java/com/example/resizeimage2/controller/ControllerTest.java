package com.example.resizeimage2.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {

    Controller controller;
    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void getUrl() throws Exception {
        String url = "![image](https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a)";
        String result = controller.getUrl(url);
        String answer = "https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a";
        System.out.println(result);
        assertEquals(answer, result);
    }

    @Test
    void applySizeTest() {
        String url = "https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a";
        String result = controller.applySize(url, 80);
        String answer = "<img src=https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a"
                + " width=\"80%\" height=\"80%\"/><br>";
        assertEquals(answer, result);
    }
}