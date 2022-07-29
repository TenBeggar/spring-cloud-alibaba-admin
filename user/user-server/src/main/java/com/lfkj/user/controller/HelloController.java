package com.lfkj.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@RefreshScope
public class HelloController {

    //config    ${prefix}-${spring.profiles.active}.${file-extension}
    //config    ${prefix}.${file-extension}
    //bootstrap.yaml
    //@Value

    @Value("${project.name:uaa}")
    private String name;

    @GetMapping
    public String pang() {
        return "pang";
    }

    @GetMapping("/name")
    public String name() {
        return name;
    }
}
