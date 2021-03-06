package com.example.books4trade.controllers;


import com.example.books4trade.models.*;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.BookReviewRepository;
import com.example.books4trade.repositories.LikeRepository;
import com.example.books4trade.repositories.UserRepository;
import com.example.books4trade.services.Utils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookReviewController {

    private final BookReviewRepository bookReviewsDao;
    private final BookRepository booksDao;
    private final UserRepository usersDao;
    private LikeRepository likesDao;

    public BookReviewController(BookReviewRepository bookReviewsDao, BookRepository bookDao, UserRepository userDao, LikeRepository likesDao) {
        this.bookReviewsDao = bookReviewsDao;
        this.booksDao = bookDao;
        this.usersDao = userDao;
        this.likesDao = likesDao;
    }
// Index of All Reviews
    @GetMapping("/reviews")
    public String showReviews (Model model) {
        List<BookReview> allReviews = bookReviewsDao.findAll();
        model.addAttribute("allReviews", allReviews);
        return "reviews/index";
    }
//  BookReviews must be based on a book in the DB, Hence the creation of a review uses
//  /BOOKS/{ID} referencing the work to be reviewed
    @GetMapping("/books/{id}/addreview")
    public String showCreateReviewForm(@PathVariable long id, Model model) {
        model.addAttribute("book", booksDao.findById(id));
        return "reviews/create";
    }
    @PostMapping("/books/{id}/submitreview")
    public String submitCreateReview(@PathVariable(name="id") long id,
                                     @RequestParam(name="title") String title,
                                     @RequestParam(name="body") String body,
                                     @RequestParam(name="rating") long rating){
        System.out.println("Posting to /submitreview with bookreview data; Title: "+ title + ", Body: " + body);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //  User user = usersDao.getById(currentUser.getId());  LEAVE FOR THE SAKE OF LEARNING getBy vs findBy default methods vs defined methods
        User user = usersDao.findByUsername(currentUser.getUsername());
        LocalDate today = LocalDate.now();
        System.out.println("User Attempting to Add Review, User ID:" + user.getId()+ ", Book ID: "+id);
        Book book = booksDao.findById(id);
         BookReview createdReview = bookReviewsDao.save(new BookReview(title, body, rating, today.toString(), user, book));
        System.out.println("New Review Saved:" + createdReview.getTitle() +" from user:"+ createdReview.getUser().getId());

        // Take the Rating and Recalculate the Book Rating
        List<BookReview> thisBooksReviews = bookReviewsDao.findByBook(book);
        System.out.println("Starting Review Rating assignment to Book ID: "+book.getId());
        System.out.println("This book returns Review QTY:" + thisBooksReviews.size());
        List<Long> allRatings = Utils.reviewRatings(thisBooksReviews);
        allRatings.add(rating);
        System.out.println("This Rating was: "+rating);
        long sum = Utils.reviewSum(allRatings);
        System.out.println("Sum of all review ratings:"+sum);
        System.out.println("Size:" + allRatings.size());
        double bookRating = (double) ( ((double) sum) / ((double) allRatings.size()));
        System.out.println("The new book rating is: "+ bookRating);
        double roundedRating =  (Math.round(bookRating*100))/ ((double) 100);
        System.out.println("The rating has been rounded to: "+ roundedRating);
        book.setRating(roundedRating);
        Book savedBook = booksDao.save(book);
        System.out.println("The book has been saved with the rating, bookid: "+savedBook.getId()+", rating: "+savedBook.getRating());
        return "redirect:/reviews/" + createdReview.getId();
    }

    // View a Single BookReview by BookReview_Id
    @GetMapping("/reviews/{id}")
    public String showIndividualReview(@PathVariable long id, Model model) {
        BookReview review = bookReviewsDao.getById(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(currentUser.getId());
        //  need to get likes for conditional
        List<Like> likes = likesDao.findLikesByReviews(review);
        //  pulling logged in user for conditional
        boolean isOwner = false;
        if(review.getUser().getId() == user.getId()){isOwner = true;}
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("review", review);
        model.addAttribute("likes", likes);
        model.addAttribute("currentUser", user);
        return "reviews/individual-review";
    }

    // View all Reviews of a Specific Book_Id
    @GetMapping("/reviews/book/{id}")
    public String showAllReviewsOfBook(@PathVariable long id, Model model){
        model.addAttribute("allReviews", bookReviewsDao.findByBook(booksDao.findById(id)));
        model.addAttribute("book", booksDao.findById(id));
        return "reviews/reviewsofbook";
    }

    // Edit an Individual Book Review
    @GetMapping("reviews/{id}/edit")
    public String showReviewEditForm(@PathVariable long id, Model model) {
        // Add a Check and Redirect if not the owner of the review
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookReview bookReview = bookReviewsDao.findById(id);
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
        public String submitReviewEditForm(@PathVariable long id, @ModelAttribute BookReview editedReview){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = usersDao.findById(currentUser.getId());
            BookReview thisReview = bookReviewsDao.findById(id);
            if(thisReview.getUser().getId() == user.getId()){
                thisReview.setTitle(editedReview.getTitle());
                thisReview.setBody(editedReview.getBody());
                thisReview.setRating(editedReview.getRating());
                editedReview = bookReviewsDao.save(thisReview);
            }
            return "redirect:/reviews/"+ editedReview.getId();
        }

     @PostMapping("/reviews/{id}/delete")
     public String deleteReview(@PathVariable long id) {
         User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         User user = usersDao.findById(currentUser.getId());
         BookReview reviewToDelete = bookReviewsDao.getById(id);
         if(reviewToDelete.getUser().getId() == user.getId()){
             bookReviewsDao.deleteById(id);
         }
        return "redirect:/reviews";
     }

    //  Liking a review
    //  Need to set count;
//    @PostMapping("/reviews/{id}/like")
//    public String likeReview(@PathVariable long id){
//         User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         User user = usersDao.getById(currentUser.getId());
//         BookReview bookReview = bookReviewsDao.getById(id);
//        //  create a new like
//         Like newLike = new Like(true, user, bookReview);
//         likesDao.save(newLike);
//        return "redirect:/reviews/" + id;
//     }

    //  Disliking a review
    //  Need to set count;
//    @PostMapping("/reviews/{id}/dislike")
//    public  String dislikeReview(@PathVariable long id){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = usersDao.getById(currentUser.getId());
//        BookReview bookReview = bookReviewsDao.getById(id);
//        //  create a new like
//        Like newDislike = new Like(false, user, bookReview);
//        likesDao.save(newDislike);
//
//        return "redirect:/reviews/" + id;
//    }
}