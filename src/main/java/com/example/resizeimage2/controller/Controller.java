package com.example.resizeimage2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/new")
    public String newForm() {
        return "new";
    }

    @PostMapping("/modify")
    public String Form() {
        return "";
    }
}
