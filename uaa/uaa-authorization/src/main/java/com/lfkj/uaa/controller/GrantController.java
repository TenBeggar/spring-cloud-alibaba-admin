package com.lfkj.uaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GrantController {

    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation() {
        return "/base-grant.html";
    }

}