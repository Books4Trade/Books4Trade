package com.example.books4trade.controllers;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.TradeItem;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.TradeRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")   //  this gives every mapping to follow a /books to start for uniformity
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

    @GetMapping("/trades")      //  will display all books available for trade
    // TODO: need to add button to NAVBAR to be redirected to trades index page
    public String viewTradables(Model model){
        List<Book> tradableBooks = ownedBooksDao.findAllTradable();
        model.addAttribute("tradables", tradableBooks);
        return "trades/index";
    }

    @GetMapping("/trade/{id}")  //  will display single tradable book with list of owners to trade with
    public String startTrade(@PathVariable long id, Model model){
        List<OwnedBook> owners = ownedBooksDao.findOwnedBooksByBook(booksDao.getById(id));
        model.addAttribute("owners", owners);
        model.addAttribute("book", booksDao.getById(id));

        return "trades/show";
    }

    //  TODO: add mapping to initiate trade; need a GET and POST
    @GetMapping("/trade/{id}/initiate")     //  {id} = represents initial book being requested to trade
    public String initiateTrade(
            @PathVariable long bookTT_id,
            @RequestParam(name = "bookCover") String bookImg,
            @RequestParam(name = "tradeBuddy") long owner_id,
            @RequestParam(name = "title") String title,
            Model model)
    {
        //  need to work on getting logged in user info
        model.addAttribute("bookCover", bookImg);
        model.addAttribute("bookTTitle", title);
        model.addAttribute("tradeBuddy", owner_id);

        return "trades/create";
    }

    //  TODO: POST mapping to create tradeItem
    @PostMapping("/trade/{id}/initiate")    //  {id} = represents initial book being requested to trade
    public String createTrade(
            @PathVariable long book_id,
            @RequestParam(name = "userInit_id") long user_id,
            @RequestParam(name = "bookCover") String bookImg,
            Model model)
    {
        //  need to redesign TradeItem and Trade Models
        //  TradeItem is missing info for
        //  receiver, 2nd book
//        TradeItem trade = new TradeItem();
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Book book = booksDao.getById(book_id);
//        User tradeInit = usersDao.getById(user_id);
//        List<OwnedBook> owner = ownedBooksDao.findOwnedBooksByBook(book);
//
//        model.addAttribute("userInit", tradeInit);
//        model.addAttribute("bookTT", book);
//        model.addAttribute("tradeBuddy", owner);

        return "redirect: /profile";
    }



}
