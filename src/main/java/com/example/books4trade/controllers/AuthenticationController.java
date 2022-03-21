package com.example.books4trade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "/users/login";
    }

    @GetMapping("/banned")
    public String youWereBanned(HttpSession session) {
        session.invalidate();
        return "users/banned";
    }

    @GetMapping("/session-invalidate")
    public String invalidateSession(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}