package com.example.books4trade.controllers;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.TradeItem;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        User reader = usersDao.findById(3);
        User trader = usersDao.findById(2);
        List<Book> reads = reader.getBooksread();
        // List<TradeItem> trades = ;
        int totalreads = reads.size();
        int totaltrades = 6; //trades.size();
        model.addAttribute("readbook", booksDao.findById(1));
        model.addAttribute("reviewbook", booksDao.findById(2));
        model.addAttribute("usertrader", trader);
        model.addAttribute("userreader", reader);
        model.addAttribute("totaltrades", totaltrades);
        model.addAttribute("totalreads", totalreads);
        return "homepage";
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
