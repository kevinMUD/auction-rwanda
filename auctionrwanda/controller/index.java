package com.auctionrwanda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {
   @RequestMapping("/index")
    public String index(){
        return "index1";
    }
}
