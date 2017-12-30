package com.onquantum.rockstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Admin on 8/26/15.
 */

@Controller
public class GuitarsController {

    @RequestMapping(value = "/guitars")
    public String guitars() {
        return "WEB-INF/pages/guitars";
    }
}
