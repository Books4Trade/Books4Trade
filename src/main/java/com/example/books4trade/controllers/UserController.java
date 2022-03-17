package com.example.books4trade.controllers;

import com.example.books4trade.models.Role;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.RoleRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private RoleRepository rolesDao;
    private PasswordEncoder passwordEncoder;


    public UserController(UserRepository userDao, RoleRepository rolesDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/users/register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user, @RequestParam(name="password-confirm") String passwordConfirm){

        // add username check for unique username
        if(user.getPassword().equals(passwordConfirm)){
            List<Role> defaultRoles = new ArrayList<>();
            defaultRoles.add(rolesDao.getById(3L));
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setRoles(defaultRoles);
            user.setEnabled(true);
            userDao.save(user);
        }// put else Error Here if passwords do not match

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getById(loggedInUser.getId());
  //      model.addAttribute("usersBooks", currentUser.getOwnedBooks());
   //     model.addAttribute("usersReviews", currentUser.getReviews());
        // Add Trades, Other Tab Info
    //    model.addAttribute("usersNotifications", currentUser.getNotifications());

//        User Information
   //     model.addAttribute("firstName", currentUser.getFirstName());
   //     model.addAttribute("lastName", currentUser.getLastName());
   //     model.addAttribute("userName", currentUser.getUsername());
    //    model.addAttribute("email", currentUser.getEmail());
    //    model.addAttribute("location", currentUser.getLocation());



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

//    For Edit Profile
    @GetMapping("/profile/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model){
        model.addAttribute("user", userDao.findByUsername(username));
        return "/users/edit";
    }

    @PostMapping("/profile/edit")
    public String submitEditForm(@PathVariable String username, @ModelAttribute User userEdited){
        User user = userDao.findByUsername(username);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userEdited.setFirstName(user.getFirstName());
        userEdited.setLastName(user.getLastName());
        userEdited.setEmail(user.getEmail());
//        userEdited.setPassword(user.getPassword());
        userEdited.setLocation(user.getLocation());

        return "redirect:/profile";


    }

}
