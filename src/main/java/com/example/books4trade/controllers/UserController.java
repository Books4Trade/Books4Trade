package com.example.books4trade.controllers;

import com.example.books4trade.models.Role;
import com.example.books4trade.models.User;
import com.example.books4trade.services.SendGridMail;
import com.example.books4trade.services.Utils;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.RoleRepository;
import com.example.books4trade.repositories.UserRepository;
import com.example.books4trade.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private SendGridMail sendGridMail;

    public UserController(UserRepository usersDao, RoleRepository rolesDao, PasswordEncoder passwordEncoder, OwnedBookRepository ownedBooksDao, EmailService emailService, SendGridMail sendGridMail) {
        this.usersDao = usersDao;
        this.rolesDao = rolesDao;
        this.ownedBooksDao = ownedBooksDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.sendGridMail = sendGridMail;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/users/register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user) throws IOException {

        // add username check for unique username
        //if(user.getPassword().equals(passwordConfirm)){
        User submittedUser = user;
            List<Role> defaultRoles = new ArrayList<>();
            defaultRoles.add(rolesDao.getById(5L));
            String random = Utils.buildRandomString();
            String hash = passwordEncoder.encode(random);
            submittedUser.setPassword(hash);
            submittedUser.setRoles(defaultRoles);
            submittedUser.setEnabled(true);
            User newUser = usersDao.save(submittedUser);
            sendGridMail.accountRegistrationSG(newUser.getUsername(), newUser.getEmail(), random);
           // emailService.accountRegistration(user);

        //}// put else Error Here if passwords do not match
        // add attribute to inform user of success and direct them to their email for temp pass
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

    @GetMapping("/profile/passwordreset")
    public String showPasswordReset(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", usersDao.findById(user.getId()));
        return "/users/password";
    }

    @PostMapping("/profile/passwordreset")
    public String submitPasswordReset(@RequestParam(name="password") String password, @RequestParam(name = "newpassword") String newpassword, @RequestParam(name="passwordconfirm") String passwordconfirm){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(currentUser.getUsername());
        if(passwordEncoder.matches(password, user.getPassword()) && newpassword.equals(passwordconfirm)){
            String hash = passwordEncoder.encode(newpassword);
            user.setPassword(hash);
            usersDao.save(user);
        }

        return "redirect:/session-invalidate";
    }

//    For Edit Profile
    @GetMapping("/profile/edit")
    public String showEditForm(Model model){
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
    public String activateUserShow(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(loggedInUser.getUsername());
        System.out.println("User Activation-Get for: " + user.getId());
        model.addAttribute("useractivate", user);
        return "/users/activate";
    }

    @PostMapping("/users/activate")
    public String activateUserSubmit(@RequestParam(name="password") String password,@RequestParam(name="password-confirm") String passwordconfirm, Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(loggedInUser.getUsername());
        if(password.equals(passwordconfirm)){
            String hash = passwordEncoder.encode(password);
            List<Role> UserRoles = new ArrayList<>();
            UserRoles.add(rolesDao.getById(3L));
            user.setPassword(hash);
            user.setRoles(UserRoles);
            System.out.println("Save Attempt For User Activation");
            User saved = usersDao.save(user);
            System.out.println("Saved user with ID:" + saved.getId());
           // User saved = usersDao.save(user);

        }
        return "redirect:/login";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable long id, Model model){
        model.addAttribute("usershown", usersDao.getById(id));
        return "users/show";
    }
}
