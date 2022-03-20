package com.example.books4trade.controllers;

import com.example.books4trade.models.Role;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.RoleRepository;
import com.example.books4trade.repositories.UserRepository;
import com.example.books4trade.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserRepository usersDao;
    private RoleRepository rolesDao;
    private OwnedBookRepository ownedBooksDao;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public UserController(UserRepository usersDao, RoleRepository rolesDao, PasswordEncoder passwordEncoder, OwnedBookRepository ownedBooksDao, EmailService emailService) {
        this.usersDao = usersDao;
        this.rolesDao = rolesDao;
        this.ownedBooksDao = ownedBooksDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
            defaultRoles.add(rolesDao.getById(5L));
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setRoles(defaultRoles);
            user.setEnabled(true);
            usersDao.save(user);
            emailService.accountRegistration(user);

        }// put else Error Here if passwords do not match

        return "redirect:/login";
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.prepareAndSend("Testing", "Did this work");
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("usersBooks", user.getOwnedBooks());
        model.addAttribute("usersReviews", user.getReviews());
    //      Add Trades, Other Tab Info
    //      model.addAttribute("usersNotifications", currentUser.getNotifications());
        return "users/profile";
    }

    @GetMapping("/forgot")
    public String showForgotPasswordForm(){
        return "users/forgot";
    }


    // REFACTOR THIS TO INCLUDE INFORMATION OTHER USERS CANNOT ACCESS
    @PostMapping("/forgot")
    public String forgotPasswordSubmit(@RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="username") String username, @RequestParam(name="password-confirm") String passwordConfirm){
        User user = usersDao.findByEmail(email);
        if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
            String hash = passwordEncoder.encode(password);

            if(password.equals(passwordConfirm)){
                user.setPassword(hash);
                usersDao.save(user);
            }
        }
        return "redirect:/login";
    }

//    For Edit Profile
    @GetMapping("/profile/edit")
    public String showEditForm( Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", usersDao.findById(user.getId()));
        return "/users/edit";
    }

    @PostMapping("/profile/edit")
    public String submitEditForm(@ModelAttribute User userEdited, Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getById(loggedInUser.getId());
        user.setFirstName(userEdited.getFirstName());
        user.setLastName(userEdited.getLastName());
        user.setEmail(userEdited.getEmail());
        user.setLocation(userEdited.getLocation());

        return "redirect:/profile";
    }

    @GetMapping("/users/activate")
    public String activateUserShow(@RequestParam(name="user") long userid, Model model) {
        System.out.println("User Activation-Get for: " + userid);
        User userToActivate = usersDao.getById(userid);
        model.addAttribute("activate-id",userid);
        model.addAttribute("useractivate", userToActivate);
        return "/users/activate";
    }

    @PostMapping("/users/activate")
    public String activateUserSubmit(){

        return "redirect:/login";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable long id, Model model){
        model.addAttribute("usershown", usersDao.getById(id));
        return "users/show";
    }
}
