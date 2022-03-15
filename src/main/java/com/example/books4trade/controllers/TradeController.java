package com.example.books4trade.controllers;

import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.TradeRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping("/books")
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
        List<OwnedBook> owners = ownedBooksDao.findOwnedBooksByBook(booksDao.getById(id));
        model.addAttribute("owners", owners);
        model.addAttribute("book", booksDao.getById(id));

        return "books/showBook_trade";
    }

//    @GetMapping("/bookowner")
//    public String

}
