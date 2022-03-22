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
        List<OwnedBook> ownedCopies = ownedBooksDao.findOwnedBooksByBook(booksDao.getById(id));
        model.addAttribute("ownedCopies", ownedCopies);
        model.addAttribute("book", booksDao.getById(id));

        return "trades/show";
    }

    //  TODO: add mapping to initiate trade; need a GET and POST
    @GetMapping("/trade/start")     //  {id} = represents initial book being requested to trade
    public String initiateTrade(
            @RequestParam(name = "tradeBuddy") long owner_id,
            @RequestParam(name = "ownedbookid") long ownedBookId,
            Model model)
    {
        //  need to work on getting logged in user info
        model.addAttribute("ownedbook", ownedBooksDao.findById(ownedBookId));
        model.addAttribute("tradebuddy", usersDao.findById(owner_id));
        System.out.println("Attempting to create trade items" + ownedBookId);
        return "trades/create";
    }

    //  TODO: POST mapping to create tradeItem
    @PostMapping("/trade/initiate")    //  {id} = represents initial book being requested to trade
    public String createTrade(
            //  yourbookid -> logged in users book id that they are trading
            @RequestParam(name = "yourbookid") long yourbookid,
            //  bookbuddyid -> user you are trading book with
            @RequestParam(name = "buddyid") long buddyid,
            //  tradebookid -> tradebuddy's book id
            @RequestParam(name = "theirbookid") long theirbookid)
    {
        System.out.println("Postmapping/trade/initiate - start");
        //  creating a new trade
        Trade newTrade = new Trade();
        Trade createdTrade = tradesDao.save(newTrade);
        System.out.println("trade: " + createdTrade.getId());

        //  TradeItem_1
        //  logged in user and their book to trade
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(currentUser.getId());
        OwnedBook userBook = ownedBooksDao.findById(yourbookid);
        TradeItem trade1 = new TradeItem(userBook, user, createdTrade);
        //  TradeItem_2
        //  BookBuddy trading with (assuming email and agreement was made prior to initiating trade)
        User bookBuddy = usersDao.findById(buddyid);
        OwnedBook buddyBook = ownedBooksDao.findById(theirbookid);
        TradeItem trade2 = new TradeItem(buddyBook, bookBuddy, createdTrade);

        TradeItem item1 = tradeItemsDao.save(trade1);
        TradeItem item2 = tradeItemsDao.save(trade2);

        createdTrade.setItem1(item1);
        createdTrade.setItem2(item2);
        Trade saved = tradesDao.save(newTrade);


        System.out.println("Attempting to create trade items " + item1);

        return "redirect:/profile";
    }



}
