package com.example.books4trade.controllers;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private BookRepository booksDao;
    private UserRepository usersDao;

    public HomeController (BookRepository booksDao, UserRepository usersDao) {
        this.booksDao = booksDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("book", booksDao.findById(1));
        model.addAttribute("user", usersDao.findById(6));
        return "homepage";
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
