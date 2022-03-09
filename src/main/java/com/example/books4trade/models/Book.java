package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    //  need to add 1:n relationship from GradeLevels

    //  need to add n:m relationship between books and users -> UserBookWatchlist Model
    @ManyToMany(mappedBy = "watchlistBooks")
    private List <User> usersWatchlist;

    //  need to add n:m relationship between books and categories -> BookCategory Model - @charles
    // https://java.codeup.com/spring/fundamentals/relationships/
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "book_category",
        joinColumns = {@JoinColumn(name = "books_id")},
        inverseJoinColumns = {@JoinColumn(name = "categories_id")}
    )
    private List<Category> categories;

    //  1:n relationship with reviews - BookReviews Model
    @OneToMany(mappedBy = "book")
    private List<BookReview> reviews;

    //  need to add 1:n relationship with trades - Trades Model (twice bookA / bookB)

    //  relationship with reads_books - ReadsBooks Model @charles
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "reads_book",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> user; //@charles


    //  CONSTRUCTORS
    public Book() {}

    public Book(List<Category> categories) {     // @charles
        this.categories = categories;
    }

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

    //  Watchlist Constructor
    public Book(long id, String title, String summary, Double rating, String book_img, List<User> usersWatchlist, List<Category> categories,
                List<BookReview> reviews, List<User> user) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.book_img = book_img;
        this.usersWatchlist = usersWatchlist;
        this.categories = categories;
        this.reviews = reviews;
        this.user = user;
    }

    //  Authors Constructors
    public Book(String title, String summary, Double rating, String book_img, Author author) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.book_img = book_img;
        this.author = author;
    }

    public Book(long id, String title, String summary, Double rating, String book_img, Author author) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.book_img = book_img;
        this.author = author;
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
    //    @charles
    public List<Category> getCategories() {
    return categories;
}
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public List<User> getUsersWatchlist() {
        return usersWatchlist;
    }
    public void setUsersWatchlist(List<User> usersWatchlist) {
        this.usersWatchlist = usersWatchlist;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
}
