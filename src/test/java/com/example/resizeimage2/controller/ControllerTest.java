package com.example.resizeimage2.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerTest {

    @Test
    void getUrl() throws Exception {
        Controller controller = new Controller();
        String url = "![image](https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a)";
        String result = controller.getUrl(url);
        String answer = "https://github.com/muyaaho/http-basic/assets/76798969/99f76ca6-9776-4382-a22e-e9fa81b5d40a";
        System.out.println(result);
        assertEquals(answer, result);
    }
}