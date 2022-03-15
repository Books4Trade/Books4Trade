package com.example.books4trade.controllers;

import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.TradeRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trades")
public class TradeController {
    private BookRepository booksDao;
    private UserRepository usersDao;
    private OwnedBookRepository ownedBooksDao;
    private TradeRepository tradesDao;

    public TradeController(BookRepository booksDao, UserRepository usersDao, OwnedBookRepository ownedBooksDao, TradeRepository tradesDao) {
        this.booksDao = booksDao;
        this.usersDao = usersDao;
        this.ownedBooksDao = ownedBooksDao;
        this.tradesDao = tradesDao;
    }

    @GetMapping("/initiate/{id}")
    public String startTrade(@PathVariable long id, Model model){
        model.addAttribute("tradeBook", booksDao.getById(id));
        return "books/trades";
    }

//    @GetMapping("/bookowner")
//    public String

}
