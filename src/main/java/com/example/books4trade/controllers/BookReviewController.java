package com.example.books4trade.controllers;


import com.example.books4trade.models.BookReview;
import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.BookReviewRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookReviewController {

    private final BookReviewRepository bookReviewDao;
    private final BookRepository booksDao;
    private final UserRepository usersDao;

    public BookReviewController(BookReviewRepository bookReviewDao, BookRepository bookDao, UserRepository userDao) {
        this.bookReviewDao = bookReviewDao;
        this.booksDao = bookDao;
        this.usersDao = userDao;
    }
// Index of All Reviews
    @GetMapping("/reviews")
    public String showReviews (Model model) {
        List<BookReview> allReviews = bookReviewDao.findAll();
        model.addAttribute("allReviews", allReviews);
        return "reviews/index";
    }
//  BookReviews must be based on a book in the DB, Hence the creation of a review uses
//  /BOOKS/{ID} referencing the work to be reviewed
    @GetMapping("/books/{id}/createreview")
    public String showReviewForm(@PathVariable long id, Model model) {
        model.addAttribute("book", booksDao.getById(id));
        model.addAttribute("bookReview", new BookReview());
        return "/reviews/create";
    }
    @PostMapping("/books/{id}/createreview")
    public String createReview(@ModelAttribute BookReview bookreview, @PathVariable long id){
        BookReview bookReview = bookreview;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookReview.setUser(currentUser);
        bookReview.setBook(booksDao.getById(id));
        bookReview.setCreatedDate(LocalDate.now());
        bookReview = bookReviewDao.save(bookReview);
        return "redirect:/reviews/" + bookReview.getId();
    }

    // View a Single BookReview by BookReview_Id
    @GetMapping("/reviews/{id}")
    public String showIndividualReview(@PathVariable long id, Model model) {
        BookReview individualReview = bookReviewDao.getById(id);
        model.addAttribute("individualReview", individualReview);
        return "reviews/individual-review";
    }

    // View all Reviews of a Specific Book_Id
    @GetMapping("/reviews/book/{id}")
    public String showAllReviewsOfBook(@PathVariable long id, Model model){
        model.addAttribute("allReviews", bookReviewDao.findByBook(booksDao.getById(id)));
        model.addAttribute("book", booksDao.getById(id));
        return "/reviews/reviewsofbook";
    }

    // Edit an Individual Book Review
    @GetMapping("reviews/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        // Add a Check and Redirect if not the owner of the review
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookReview bookReview = bookReviewDao.getById(id);
        if(bookReview.getUser().getId() == currentUser.getId()){
            // If the Auth User is the owner, add the bookReview to the model and return the form
            model.addAttribute("bookReview", bookReview);
            return"reviews/edit";
        } else {
            // Otherwise, redirect to the review index
            return "redirect:/reviews";
        }
    }

    @PostMapping("/reviews/{id}/edit")
        public String submitEdit(@PathVariable long id, @ModelAttribute BookReview editedReview){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if( editedReview.getUser().getId() == currentUser.getId()){
                editedReview.setUser(currentUser);
                editedReview.setBook(bookReviewDao.getById(id).getBook());
                editedReview.setCreatedDate(bookReviewDao.getById(id).getCreatedDate());
                editedReview = bookReviewDao.save(editedReview);
            }
            return "redirect:/reviews/"+ editedReview.getId();
        }

     @PostMapping("/reviews/{id}/delete")
     public String deleteReview(@PathVariable long id) {
         User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         BookReview reviewToDelete = bookReviewDao.getById(id);
         if(reviewToDelete.getUser().getId() == currentUser.getId()){
             bookReviewDao.deleteById(id);
         }
        return "redirect:/reviews";
     }
}