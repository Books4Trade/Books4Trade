package com.example.books4trade.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.*;


@Entity
@Table(name = "book_reviews")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    @Size(max = 2000, message = "MAX: 2000 characters .")
    private String body;

    @Column
    private long rating;

    @Column
    private String createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviews")
    private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BookReview() {}

    public BookReview(String title, String body, long rating, String createdDate, User user, Book book){
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
        this.user = user;
        this.book = book;
    }

    public BookReview(String title, String body, long rating, String createdDate, List<Like> likes, User user, Book book) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdDate = createdDate;
        this.likes = likes;
        this.user = user;
        this.book = book;
    }

    public BookReview(long id, String title, String body, long rating, String createdDate, Book book, User user){
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public String getCreatedDate() {return createdDate;}
    public void setCreatedDate(String createdOn) {
        this.createdDate = createdDate;
    }
    public List<Like> getLikes() {
        return likes;
    }
    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
