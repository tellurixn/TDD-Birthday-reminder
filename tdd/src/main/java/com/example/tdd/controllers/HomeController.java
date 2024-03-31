package com.example.tdd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        //todo сверстать полноценную домашнюю страницу
        return "home";
    }
}
