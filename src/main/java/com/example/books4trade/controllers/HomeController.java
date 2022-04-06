package com.example.books4trade.controllers;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.BookReview;
import com.example.books4trade.models.TradeItem;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.TradeItemsRepository;
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
    private TradeItemsRepository tradeItemsDao;

    public HomeController (BookRepository booksDao, UserRepository usersDao, TradeItemsRepository tradeItemsDao) {
        this.booksDao = booksDao;
        this.usersDao = usersDao;
        this.tradeItemsDao = tradeItemsDao;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        User reader = usersDao.findById(3);
        User trader = usersDao.findById(2);
        List<User> allUsers = usersDao.findAll();
        User topReader = usersDao.findById(3);
        int numberRead = 0;
        User topTrader;
        int numberTraded = 0;
        for(User user : allUsers){
            List<Book> booksRead = user.getBooksread();
            List<TradeItem> tradersItems = tradeItemsDao.findTradeItemsByUser(user);
            if(booksRead.size() > numberRead){
                numberRead  = booksRead.size();
                topReader = user;
            }
            if(tradersItems.size() > numberTraded){
                numberTraded = tradersItems.size();
                topTrader = user;
            }
        }
        List<Book> reads = reader.getBooksread();
        // List<TradeItem> trades = ;
        int totalreads = reads.size();
        int totaltrades = 6; //trades.size();

        List<Book> allBooks = booksDao.findAll();
        Book mostReadBook;
        int numberOfReads = 0;
        Book topRatedBook;
        double highestRating = 0;
        int numberOfReviews = 0;
        for(Book book : allBooks){
            List<User> usersRead = book.getReaders();
            List<BookReview> reviewsOfBook = book.getReviews();
            if(usersRead.size() > numberOfReads){
                numberRead  = usersRead.size();
                mostReadBook = book;
            }
            if(book.getRating() != null){
                if(book.getRating() > highestRating) {
                    highestRating = book.getRating();
                    numberOfReviews = reviewsOfBook.size();
                    topRatedBook = book;
                }
            }
        }

        model.addAttribute("readbook", booksDao.findById(1));
        model.addAttribute("reviewbook", booksDao.findById(2));
        model.addAttribute("usertrader", trader);
        model.addAttribute("userreader", topReader);
        model.addAttribute("totaltrades", totaltrades);
        model.addAttribute("totalreads", totalreads);
        return "homepage";
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
