package com.example.books4trade.controllers;


import com.example.books4trade.models.BookReview;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.BookReviewRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookReviewController {

    private final BookReviewRepository bookReviewDao;
    private final BookRepository bookDao;
    private final UserRepository userDao;

    public BookReviewController(BookReviewRepository bookReviewDao, BookRepository bookDao, UserRepository userDao) {
        this.bookReviewDao = bookReviewDao;
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @GetMapping("/reviews")
    public String showReviews (Model model) {
//        List <BookReview> bookReview = new ArrayList<>();
//        BookReview review1 = new BookReview(1,"Awesome book.", "Must read.", 1 );
//        bookReview.add(review1);
//        model.addAttribute("allReviews", bookReview);

        List<BookReview> allReviews = bookReviewDao.findAll();
        model.addAttribute("allReviews", allReviews);
        return "reviews/index";
    }

    @GetMapping("/reviews/{id}")
    public String showIndividualReview (@PathVariable long id, Model model) {
        BookReview individualReview = bookReviewDao.getById(id);
        model.addAttribute("reviewId", id);
        model.addAttribute("individualReview",individualReview);
        return "reviews/individual-review";
    }

    @GetMapping("/reviews/create")
    public String showReviewForm(Model model) {
        model.addAttribute("bookReview", new BookReview());
        return "/reviews/create";
    }

    @PostMapping("/reviews/create")
    public String createReview (@ModelAttribute BookReview bookReview) {
//        bookReview.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
         bookReviewDao.save(bookReview);
         return "redirect:/reviews";
    }

    @GetMapping("reviews/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("review",bookReviewDao.getById(id));
        return "/reviews/edit";
    }

//    @PostMapping("/reviews/{id}/edit"){
//        public String submitEdit(@ModelAttribute BookReview editedReview, @PathVariable long id) {
//            BookReview bookToEdit = bookReviewDao.getById(id);
//            bookToEdit.setTitle(bookToEdit.getTitle());
//            bookToEdit.setBody(bookToEdit.getBody());
//            bookReviewDao.save(bookToEdit);
//            return "redirect:/reviews";
//        }
//    }

    @PostMapping("/reviews/{id}/edit")
        public String submitEdit(@ModelAttribute BookReview editedReview, @PathVariable long id){
            editedReview = bookReviewDao.getById(id);
            editedReview.setTitle(editedReview.getTitle());
            editedReview.setBody(editedReview.getBody());
            bookReviewDao.save(editedReview);
            return "redirect:/reviews";
        }

     @PostMapping("/reviews/{id}/delete")
     public String deleteReview(@PathVariable long id) {
        bookReviewDao.deleteById(id);
        return "redirect:/reviews";
     }







}

