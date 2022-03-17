package com.example.books4trade.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.*;


@Entity
@Table(name = "book_reviews")
public class BookReview {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    @Size(max = 2000, message = "MAX: 2000 characters .")
    private String body;

    @Column(nullable = false)
    private long rating;

    @Column(nullable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookReview() {}

    public BookReview(String title, String body, long rating, Date createdDate){
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
    }
    public BookReview(long id, String title, String body, long rating, Date createdDate){
        this.id = id;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
    }
    public BookReview(long id, String title, String body, long rating, Date createdDate, Book book){
        this.id = id;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
        this.book = book;
    }
    public BookReview(long id, String title, String body, long rating, Date createdDate, Book book, User user){
        this.id = id;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
        this.user = user;
        this.book = book;
    }
    // Relationship Getters and Setters
    public void setUser(User user){ this.user = user;}
    public User getUser(){return user;}
    public void setBook(Book book){ this.book = book;}
    public Book getBook(){return book;}

    // Basic Field Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public long getRating() {
        return rating;
    }
    public void setRating(long rating) {
        this.rating = rating;
    }
    public Date getCreatedDate() {return createdDate;}
    public void setCreatedDate(String createdOn) {
        this.createdDate = createdDate;
    }
}
