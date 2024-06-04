package com.example.resizeimage2.controller;

import com.example.resizeimage2.AllChangeService;
import com.example.resizeimage2.dto.Form;
import java.io.IOException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    private AllChangeService allChangeService;

    @GetMapping("/new")
    public String newForm() {
        return "new";
    }


    @PostMapping("/modify")
    public String Form2(Form form) throws IOException {
        allChangeService = new AllChangeService(form);
        return "redirect:/result";
    }


    @GetMapping("/result")
    public String show(Model model) {
        model.addAttribute("result", allChangeService.getOutputString());
        return "result";
    }

}
