package com.example.books4trade.controllers;

import com.example.books4trade.models.*;
import com.example.books4trade.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")   //  this gives every mapping to follow a /books to start for uniformity
public class TradeController {
    private BookRepository booksDao;
    private UserRepository usersDao;
    private OwnedBookRepository ownedBooksDao;
    private TradeRepository tradesDao;
    private TradeItemsRepository tradeItemsDao;

    public TradeController(BookRepository booksDao, UserRepository usersDao, OwnedBookRepository ownedBooksDao, TradeRepository tradesDao, TradeItemsRepository tradeItemsDao) {
        this.booksDao = booksDao;
        this.usersDao = usersDao;
        this.ownedBooksDao = ownedBooksDao;
        this.tradesDao = tradesDao;
        this.tradeItemsDao = tradeItemsDao;
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
            @RequestParam(name = "bookBuddy_id") long bookBuddy_id,
            @RequestParam(name = "userBook_id") long userBook_id)
    {

        //  creating a new trade
        Trade newTrade = new Trade();
        tradesDao.save(newTrade);
        //  TradeItem_1
        //  logged in user and their book to trade
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OwnedBook userBook = ownedBooksDao.getById(userBook_id);
        TradeItem trade1 = new TradeItem(userBook, currentUser, newTrade);
        //  TradeItem_2
        //  BookBuddy trading with (assuming email and agreement was made prior to initiating trade)
        User bookBuddy = usersDao.getById(bookBuddy_id);
        OwnedBook buddyBook = ownedBooksDao.getById(book_id);
        TradeItem trade2 = new TradeItem(buddyBook, bookBuddy, newTrade);

        tradeItemsDao.save(trade1);
        tradeItemsDao.save(trade2);

        return "redirect: /profile";
    }



}
