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

        User topReader = usersDao.findById(3);
        int numberUserHasRead = topReader.getBooksread().size();
        int numberReviewed = topReader.getReviews().size();

        User topTrader = usersDao.findById(2);
        int numberTraded = tradeItemsDao.findTradeItemsByUser(topTrader).size();
        int numberOwned = topTrader.getOwnedBooks().size();

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
        List<Book> checkReadersList = topReader.getBooksread();
        System.out.println("Top Reader:" + topReader.getUsername() + " has read "+ numberUserHasRead + "books.");
        for(Book book : checkReadersList){
            System.out.println(book.getTitle());
        }



        Book mostReadBook = booksDao.findById(1);
        int numberOfReads = mostReadBook.getReaders().size();
        int numberOfCopies = mostReadBook.getOwnedBooks().size();

        Book topRatedBook = booksDao.findById(2);


        double highestRating = 0;
        int numberOfReviews = 0;
        List<Book> allBooks = booksDao.findAll();
        for(Book book : allBooks){
            List<BookReview> reviewsOfBook = book.getReviews();
            if(book.getReaders().size() > numberOfReads){
                mostReadBook = book;
                numberOfReads  = book.getReaders().size();

            }
            if(book.getRating() != null){
                if(book.getRating() > highestRating) {
                    highestRating = book.getRating();
                    numberOfReviews = reviewsOfBook.size();
                    topRatedBook = book;
                }
            }
        }

        model.addAttribute("mostreadbook", mostReadBook);
        model.addAttribute("reviewbook", booksDao.findById(2));

        model.addAttribute("topreader", topReader);
        model.addAttribute("totalreads", numberUserHasRead);
        model.addAttribute("totalreviews", numberReviewed);

        model.addAttribute("toptrader", topTrader);
        model.addAttribute("totaltrades", numberTraded);
        model.addAttribute("totalowned", numberOwned);

        model.addAttribute("");

        return "homepage";
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
