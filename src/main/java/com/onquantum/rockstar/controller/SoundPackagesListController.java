package com.onquantum.rockstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Admin on 12/17/15.
 */

@Controller
public class SoundPackagesListController {
    @RequestMapping("/sound_pack_list")
    public String sound_pack_list() {
        return "WEB-INF/pages/sound_pack_list";
    }
}
