package com.example.books4trade.controllers;

import com.example.books4trade.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    private BookRepository booksDao;

    public BookController(BookRepository bookDao){
        this.booksDao = bookDao;
    }

    @GetMapping("/books")
    public String ShowBooks(Model model){
        model.addAttribute("allBooks", booksDao.findAll());
        return "books/index";
    }
}
