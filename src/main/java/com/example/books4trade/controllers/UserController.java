package com.example.books4trade.controllers;

import com.example.books4trade.models.*;
import com.example.books4trade.repositories.*;
import com.example.books4trade.services.SendGridMail;
import com.example.books4trade.services.Utils;
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
   // private EmailService emailService;
    private SendGridMail sendGridMail;
    private TradeRepository tradesDao;
    private TradeItemsRepository tradeItemsDao;

    public UserController(UserRepository usersDao, RoleRepository rolesDao, PasswordEncoder passwordEncoder, OwnedBookRepository ownedBooksDao,
                          SendGridMail sendGridMail, TradeRepository tradesDao, TradeItemsRepository tradeItemsDao) {
        this.usersDao = usersDao;
        this.rolesDao = rolesDao;
        this.ownedBooksDao = ownedBooksDao;
        this.passwordEncoder = passwordEncoder;
    //    this.emailService = emailService;
        this.sendGridMail = sendGridMail;
        this.tradesDao = tradesDao;
        this.tradeItemsDao = tradeItemsDao;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
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

    @GetMapping("/profile")
    public String showProfile(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(currentUser.getUsername());
        List<TradeItem> userTrades = tradeItemsDao.findTradeItemsByNotSentByUser(user);

        model.addAttribute("tradeItems", userTrades);
        model.addAttribute("user", user);
        model.addAttribute("usersBooks", user.getOwnedBooks());
        model.addAttribute("usersReviews", user.getReviews());
        model.addAttribute("usersReads", user.getBooksread());
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
    public String forgotPasswordSubmit(
            @RequestParam(name="firstname") String firstname, @RequestParam(name="lastname") String lastname,
            @RequestParam(name="username") String username, @RequestParam(name="email") String email){
        User user = usersDao.findByUsername(username);
        if ((email.equals(user.getEmail())) && (lastname.equals(user.getLastName())) && (firstname.equals(user.getFirstName()))) {
            // Now get the new random password, email it, hash it, and reset the password, then save the user

//            String hash = passwordEncoder.encode(password);
//
//            if(password.equals(passwordConfirm)){
//                user.setPassword(hash);
//                usersDao.save(user);
//            }
        }
        return "redirect:/login";
    }

    @GetMapping("/profile/passwordreset")
    public String showPasswordReset(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", usersDao.findById(user.getId()));
        return "users/password";
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
        return "users/edit";
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
        return "users/activate";
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

    @GetMapping("/users")
    public String showUsersIndex(Model model){
        model.addAttribute("searched", false);
        model.addAttribute("allusers", usersDao.findAll());
        return "users/index";
    }

    @PostMapping("/users/search")
    public String searchBooks(@RequestParam(name = "query") String query, Model model){
        model.addAttribute("allusers", usersDao.searchByUsernameLike(query));
        model.addAttribute("searchedby", query);
        model.addAttribute("searchedquery", query);
        model.addAttribute("searched", true);
        return "users/index";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable long id, Model model){
        model.addAttribute("usershown", usersDao.getById(id));
        return "users/show";
    }
}
