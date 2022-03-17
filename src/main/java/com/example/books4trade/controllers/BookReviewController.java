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

//
    @GetMapping("/reviews/{id}")
    public String showIndividualReview (@PathVariable long id, Model model) {
        BookReview individualReview = bookReviewDao.getById(id);
        model.addAttribute("reviewId", id);
        model.addAttribute("individualReview",individualReview);
        return "reviews/individual-review";
    }
//



    @GetMapping("/reviews/create")
    public String showReviewForm(Model model) {
        model.addAttribute("bookReview", new BookReview());
        return "/reviews/create";
    }

//

//    edited by mike
    @PostMapping("/reviews/create")
    public String createReview (@RequestParam(name= "rating") String rating, @RequestParam(name="body") String body, @RequestParam (name= "title") String title) {
        BookReview bookReview = new BookReview();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookReview.setBody(body);
        bookReview.setTitle(title);
        bookReview.setRating(rating);
        bookReview.setUser(currentUser);
        //        bookReview.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

         bookReview = bookReviewDao.save(bookReview);
         return "redirect:/reviews/" +bookReview.getId();
    }

//    today
    @GetMapping("/reviews/{id}")
    public String showAllReviewsOfBook(@PathVariable long id, Model model){
        model.addAttribute("review", bookReviewDao.getById(id));
        model.addAttribute("allReviews", bookReviewDao.findBookReviewsBy(booksDao.getById(id));
        return "/reviews/individual-review";
    }
//


    @GetMapping("reviews/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("editReview",bookReviewDao.getById(id));
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

    @PostMapping("/reviews/{id}/user/{reviewid}/edit")
        public String submitEdit(@PathVariable long id, @PathVariable long reviewid, @ModelAttribute BookReview editedReview){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            BookReview reviewToEdit = bookReviewDao.getById(editedReview.getId());
            reviewToEdit.setUser(currentUser);
            reviewToEdit.setTitle(editedReview.getTitle());
            reviewToEdit.setBody(editedReview.getBody());
            reviewToEdit.setRating(editedReview.getRating());
            reviewToEdit = bookReviewDao.save(editedReview);
            return "redirect:/reviews" + id + "/user/" + reviewToEdit.getId();
        }

    @GetMapping("reviews/{id}/user/{reviewid}/edit")
    public String editReview(@PathVariable long id, @PathVariable long reviewid, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookReview reviewToEdit = bookReviewDao.getById(reviewid);
        model.addAttribute("editReview",bookReviewDao.getById(id));
        return "/reviews/edit";
    }


    //    mike
     @PostMapping("/reviews/{id}/delete")
     public String deleteReview(@PathVariable long id) {
         User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         BookReview reviewToDelete = bookReviewDao.getById(id);
         if(reviewToDelete.getUser().getId() == currentUser.getId()){
             bookReviewDao.deleteById(id);
         }

        return "redirect:/reviews";
     }





    @GetMapping("/reviews/{id}")
    public String showUserReview(@PathVariable long id, Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookReview showReview = bookReviewDao.getById(id);
        boolean isOwner = false;
        if(showReview.getUser().getId() == currentUser.getId()){
            isOwner = true;
        }
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("review", bookReviewDao.getById(id));
        model.addAttribute("showBook", showReview);
        return "/reviews/individual-review";
    }
//



}

