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
        //  grabbing all tradables and will use condition on front end
        //  need user for conditional
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(currentUser.getId());
        List<OwnedBook> ownedBooks = ownedBooksDao.findUserTradableBooks(user);

        //  need to work on getting logged in user info
        model.addAttribute("ownedbook", ownedBooksDao.findById(ownedBookId));
        model.addAttribute("tradebuddy", usersDao.findById(owner_id));
        model.addAttribute("mytradables", ownedBooks);
        model.addAttribute("loggedinuser", user);
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
        System.out.println("Yourbook Id: " + yourbookid);

        //  creating a new trade
        Trade newTrade = new Trade();
        Trade createdTrade = tradesDao.save(newTrade);
        System.out.println("trade: " + createdTrade.getId());

        //  TradeItem_1
        //  logged in user and their book to trade
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(currentUser.getId());
        OwnedBook userBook = ownedBooksDao.findById(yourbookid);
        System.out.println("Userbook from DAO: " + userBook.getId());
        TradeItem trade1 = new TradeItem(userBook, user, createdTrade);
        TradeItem item1 = tradeItemsDao.save(trade1);
        createdTrade.setItem1(item1);
        System.out.println("Trade item1: " + item1 + "userbook: " + userBook.getId() + "trade item1 bookID: " + item1.getOwnedBook());

        //  TradeItem_2
        //  BookBuddy trading with (assuming email and agreement was made prior to initiating trade)
        User bookBuddy = usersDao.findById(buddyid);
        OwnedBook buddyBook = ownedBooksDao.findById(theirbookid);
        TradeItem trade2 = new TradeItem(buddyBook, bookBuddy, createdTrade);
        TradeItem item2 = tradeItemsDao.save(trade2);
        createdTrade.setItem2(item2);
        System.out.println("Trade item2: " + trade2);

        Trade saved = tradesDao.save(createdTrade);
        System.out.println("Attempting to create trade items " + item1);

        return "redirect:/profile";
    }

    //  Mapping to setOwnedBook to logged in user and removing from TradeBuddy
    @PostMapping("/trade/received")
    public String tradeReceived(
        @RequestParam(name = "mybookid") long mybookid,
        @RequestParam(name = "item") long itemid,
        @RequestParam(name = "buddyId") User buddyId)
    {
        //  mybookid == trade_items.sent_book_id
        System.out.println("My book_id: " + mybookid);
        //  itemid == trade_item_id
        System.out.println("My tradeItem Id: " + itemid);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(currentUser.getId());


        //  setting confirm_sent to true
        TradeItem myItem = tradeItemsDao.findById(itemid);
        myItem.setConfirm_sent(true);
        tradeItemsDao.save(myItem);
        System.out.println(myItem.isConfirm_sent());

        //  setting OwnedBook user to Tradebuddy
        OwnedBook mySentBook = ownedBooksDao.findById(mybookid);
        System.out.println("Book I'm sending to my TradeBuddy: " + mySentBook.getBook().getTitle());
        mySentBook.setUser(buddyId);
        ownedBooksDao.save(mySentBook);

        return "redirect:/profile";
    }
}
