package com.eh.cloud.tools.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: caopeihe
 * date: 2020/3/4 10:24
 * desc: IndexController
 */
@Controller
public class IndexController {
    @RequestMapping(value = "index")
    public String index(){
        return "";
    }
}
