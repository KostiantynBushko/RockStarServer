package com.onquantum.rockstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Admin on 12/20/15.
 */

@Controller
public class SoundPackage {
    @RequestMapping(value = "/sound_package")
    public String sound_package() {
        return "WEB-INF/pages/sound_pack";
    }
}
