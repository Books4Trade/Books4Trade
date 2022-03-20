package com.example.books4trade.controllers;

import com.example.books4trade.models.User;
import com.example.books4trade.repositories.*;
import com.example.books4trade.services.EmailService;
import com.example.books4trade.services.SendGridMail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AdminController {

    private UserRepository usersDao;
    private RoleRepository rolesDao;
    private BookRepository booksDao;
    private BookReviewRepository reviewsDao;
    private OwnedBookRepository ownedBooksDao;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    private SendGridMail sendGridMail;

    public AdminController(UserRepository usersDao, RoleRepository rolesDao, PasswordEncoder passwordEncoder, BookRepository booksDao, BookReviewRepository reviewsDao, OwnedBookRepository ownedBooksDao, EmailService emailService, SendGridMail sendGridMail) {
        this.usersDao = usersDao;
        this.rolesDao = rolesDao;
        this.booksDao = booksDao;
        this.reviewsDao = reviewsDao;
        this.ownedBooksDao = ownedBooksDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.sendGridMail = sendGridMail;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return"/admin/dashboard";
    }

    @GetMapping("/test/email")
    public String testEmail() throws IOException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = usersDao.findByUsername(currentUser.getUsername());
        System.out.println("User Id"+user.getId());
        sendGridMail.accountRegistrationSG(user.getUsername(), user.getEmail(), "test");
        return "redirect:/books";
    }
}
