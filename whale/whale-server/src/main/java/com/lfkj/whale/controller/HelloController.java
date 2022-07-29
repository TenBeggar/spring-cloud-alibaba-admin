package com.lfkj.whale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class HelloController {

    @GetMapping
    public String pang() {
        return "pang";
    }
}
