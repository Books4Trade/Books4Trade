package com.example.books4trade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String ShowLoginPage(){
        return "/users/login";
    }
}
