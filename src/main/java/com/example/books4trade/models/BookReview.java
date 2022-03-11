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
    @NotBlank(message = "Must have a title")
    @Size(max = 255, message = "Title MINIMUM: 3, MAX: 255 characters .")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Must have a body")
    @Size(max = 1020, message = "Title MINIMUM: 10, MAX: 500 characters .")
    private String body;

    @Column(nullable = true)
    @Value("${file-upload-path}") //will need a file upload form
    private String uploadPath;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String createdOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public BookReview() {
    }

    public BookReview(String title, String body, String rating, String createdOn) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createdOn = createdOn;
    }

    public BookReview(long id, String title, String body, String uploadPath, String rating, String createdOn) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.uploadPath = uploadPath;
        this.rating = rating;
        this.createdOn = createdOn;
    }

    public BookReview(long id, String title, String body, String rating) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.uploadPath = uploadPath;
        this.rating = rating;
    }

    public BookReview(String title, String body, String uploadPath, String rating, String createdOn) {
        this.title = title;
        this.body = body;
        this.uploadPath = uploadPath;
        this.rating = rating;
        this.createdOn = createdOn;
    }

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

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
