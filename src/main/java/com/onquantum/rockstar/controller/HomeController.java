package com.onquantum.rockstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Admin on 8/11/15.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "WEB-INF/pages/home";
    }
}
