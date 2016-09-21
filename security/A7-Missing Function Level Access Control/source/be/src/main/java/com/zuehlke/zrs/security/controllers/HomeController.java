package com.zuehlke.zrs.security.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nesp on 21-Sep-16.
 */
@Controller
@EnableAutoConfiguration
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello world!";
    }

}
