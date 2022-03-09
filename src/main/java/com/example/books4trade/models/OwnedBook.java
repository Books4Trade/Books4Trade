package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "owned_books")
public class OwnedBook {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Please enter either Poor/Fair/Good/Better/New.")
    @Size(min = 3, max = 6, message = "Condition should be between 3 and 6 characters long")
    private String bookCondition;

    @NotNull
    @AssertTrue
    private boolean isOwned;

    @NotNull
    @AssertFalse
    private boolean isTradeable;

    //  need to add 1:n relationship from Users
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //  need to add 1:n relationship from Types

    //  need to add 1:n relationship with Books
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookOwned;

    //  CONSTRUCTORS
    public OwnedBook() {}

    public OwnedBook(String bookCondition, boolean isOwned, boolean isTradable) {
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradable;
    }

    public OwnedBook(long id, String bookCondition, boolean isOwned, boolean isTradable) {
        this.id = id;
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradable;
    }

    //  bookOwned Book Constructor
    public OwnedBook(String bookCondition, boolean isOwned, boolean isTradeable, User user, Book bookOwned) {
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradeable;
        this.user = user;
        this.bookOwned = bookOwned;
    }

    //  seeder Constructor ALL
    public OwnedBook(long id, String bookCondition, boolean isOwned, boolean isTradeable, User user, Book bookOwned) {
        this.id = id;
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradeable;
        this.user = user;
        this.bookOwned = bookOwned;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBookCondtion() {
        return bookCondition;
    }
    public void setBookCondtion(String bookCondtion) {
        this.bookCondition = bookCondtion;
    }
    public boolean isOwned() {
        return isOwned;
    }
    public void setOwned(boolean owned) {
        isOwned = owned;
    }
    public boolean isTradable() {
        return isTradeable;
    }
    public void setTradable(boolean tradable) {
        isTradeable = tradable;
    }
    public Book getBookOwned() {
        return bookOwned;
    }
    public void setBookOwned(Book bookOwned) {
        this.bookOwned = bookOwned;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
