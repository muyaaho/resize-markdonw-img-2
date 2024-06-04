package com.example.resizeimage2.controller;

import com.example.resizeimage2.AllChangeService;
import com.example.resizeimage2.dto.Form;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    private String result;
    private AllChangeService allChangeService;

    @GetMapping("/new")
    public String newForm() {
        return "new";
    }

    @GetMapping("/all-new")
    public String newForm2() {
        return "allNew";
    }

    @PostMapping("/modify")
    public String Form(Form form) throws Exception {

        System.out.println(form.toString());
        result = getResult(form);
        return "redirect:/result";
    }

    @PostMapping("/modify2")
    public String Form2(Form form) throws IOException {
        allChangeService = new AllChangeService(form);
        return "redirect:/result2";
    }

    @GetMapping("/result")
    public String show(Model model) {
        model.addAttribute("resultUrl", result);
        return "result";
    }

    @GetMapping("/result2")
    public String show2(Model model) {
        model.addAttribute("allResultUrl", allChangeService.getOutputString());
        return "allResult";
    }


    public String reUrl(String url) throws Exception {

        String re = "(ftp|http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:[0-9]+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?[^)]";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(url);
        if (!m.find()) {
            throw new Exception();
        }
        return m.group();
    }

    public String applySize(String url, int size) {
        String strSize = Integer.toString(size);
        return "<img src=" + url +
                " width=\"" + strSize +
                "%\" height=\"" + strSize +
                "%\"/><br>";
    }

    public String getResult(Form form) throws Exception {
        return applySize(reUrl(form.getUrl()), form.getSize());
    }
}
