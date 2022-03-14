package com.example.books4trade.controllers;

import com.example.books4trade.models.User;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Email;
import java.util.List;

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
        if(user.getPassword().equals(passwordConfirm)){
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setRole(1);
            userDao.save(user);
        }// put else Error Here if passwords do not match

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getById(loggedInUser.getId());
        model.addAttribute("usersBooks", currentUser.getOwnedBooks());
        model.addAttribute("usersReviews", currentUser.getReviews());
        // Add Trades, Other Tab Info
        model.addAttribute("usersNotifications", currentUser.getNotifications());


        return "users/profile";

    }

    @GetMapping("/forgot")
    public String showForgotPasswordForm(){

        return "users/forgot";
    }




    @PostMapping("/forgot")
    public String forgotPasswordSubmit(@RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="username") String username, @RequestParam(name="password-confirm") String passwordConfirm){
        User user = userDao.findByEmail(email);

        if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
            String hash = passwordEncoder.encode(password);

            if(password.equals(passwordConfirm)){
                user.setPassword(hash);
                userDao.save(user);
            }

        }

        return "redirect:/login";
    }

}
