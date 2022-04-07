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

        // Setting an admin as the default User for the "Top Reader" and "Top Trader" for the Homepage
        // With their number of Books Read and Books Traded as the Top Numbers
        User topReader = usersDao.findById(3);
        int numberUserHasRead = topReader.getBooksread().size();
        int numberReviewed = topReader.getReviews().size();

        User topTrader = usersDao.findById(2);
        int numberTraded = tradeItemsDao.findTradeItemsByUser(topTrader).size();
        int numberOwned = topTrader.getOwnedBooks().size();

        // Iterator for All Users, if they have read or traded more than the default users, they replace them with their
        // Count of Reads/Reviews and Trades/OwnedBooks
        // topTrader is the User with the most trades, topReader is the User with the most books read.
        List<User> allUsers = usersDao.findAll();
        for(User user : allUsers){
            if(user.getBooksread().size() > numberUserHasRead){
                topReader = user;
                numberUserHasRead  = user.getBooksread().size();
                numberReviewed = user.getReviews().size();
            }
            if(tradeItemsDao.findTradeItemsByUser(user).size() > numberTraded){
                topTrader = user;
                numberTraded = tradeItemsDao.findTradeItemsByUser(user).size();
                numberOwned = user.getOwnedBooks().size();
            }
        }

        // Repeating process for Top Books by setting a default book for the Most Read Book and the Highest Rated Book
        Book mostReadBook = booksDao.findById(1);
        int numberOfReads = mostReadBook.getReaders().size();
        int numberOfCopies = mostReadBook.getOwnedBooks().size();

        Book topRatedBook = booksDao.findById(2);
        double highestRating = topRatedBook.getRating();
        int numberOfReviews = topRatedBook.getReviews().size();

        // Iterator for determining Top Books by Number of Times Read (mostReadBook) & Highest Rating value (topRatedBook)
        List<Book> allBooks = booksDao.findAll();
        for(Book book : allBooks){
            List<BookReview> reviewsOfBook = book.getReviews();
            if(book.getReaders().size() > numberOfReads){
                mostReadBook = book;
                numberOfReads  = book.getReaders().size();
                numberOfCopies = book.getOwnedBooks().size();
            }
            if(book.getRating() != null){
                if(book.getRating() > highestRating) {
                    highestRating = book.getRating();
                    numberOfReviews = reviewsOfBook.size();
                    topRatedBook = book;
                }
            }
        }

        // Top Reader and Top Trader attributes assignment
        model.addAttribute("topreader", topReader);
        model.addAttribute("totalreadsforuser", numberUserHasRead);
        model.addAttribute("totalreviewsforuser", numberReviewed);

        model.addAttribute("toptrader", topTrader);
        model.addAttribute("totaltrades", numberTraded);
        model.addAttribute("totalowned", numberOwned);

        // Most Read and Highest Rated Book attributes assignment
        model.addAttribute("mostreadbook", mostReadBook);
        model.addAttribute("totalreadsforbook", numberOfReads);
        model.addAttribute("totalcopiesforbook", numberOfCopies);

        model.addAttribute("highestratedbook", topRatedBook);
        model.addAttribute("highestrating", highestRating);
        model.addAttribute("totalreviewsforbook", numberOfReviews);

        return "homepage";
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
