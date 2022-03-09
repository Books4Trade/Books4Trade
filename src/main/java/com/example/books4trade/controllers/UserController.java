package com.example.books4trade.controllers;

import com.example.books4trade.models.User;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/users/register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user, @RequestParam(name="password-confirm") String passwordConfirm){
        if(user.getPassword() == passwordConfirm){
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setRole(1);
            userDao.save(user);
        }// put else Error Here if passwords do not match


        return "redirect:/login";
    }
}
