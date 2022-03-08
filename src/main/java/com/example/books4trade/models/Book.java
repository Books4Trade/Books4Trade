package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Books must have a title.")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 Characters long")
    private String title;

    @NotBlank(message = "Summary must have some content.")
    @Size(min = 10, max = 500, message = "Summary should be at least 10 characters long and no more than 500 characters long.")
    private String summary;

    @Column
    private Double rating;

    @Column
    private String book_img;

    //  need to add 1:n relationship from Authors

    //  need to add 1:n relationship from GradeLevels

    //  need to add n:m relationship between books and users -> UserBookWatchlist Model

    //  need to add n:m relationship between books and categories -> BookCategory Model

    //  need to add 1:n relationship with reviews - BookReviews Model

    //  need to add 1:n relationship with trades - Trades Model (twice bookA / bookB)

    //  need to add 1:n relationship with reads_books - ReadsBooks Model

    //  CONSTRUCTORS
    public Book() {}

    public Book(String title, String summary, Double rating, String book_img) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.book_img = book_img;
    }

    public Book(long id, String title, String summary, Double rating, String book_img) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.book_img = book_img;
    }

    //  GETTERS/SETTERS
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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getBook_img() {
        return book_img;
    }
    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }
}
