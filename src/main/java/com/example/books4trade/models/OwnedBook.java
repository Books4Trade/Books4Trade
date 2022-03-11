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

    @Column
    private String bookCondition;

    @Column
    private boolean isOwned;

    @Column
    private boolean isTradeable;

    //  need to add 1:n relationship from Users
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //  need to add 1:n relationship from Types
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

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

    //  bookOwned User Constructor
    public OwnedBook(String bookCondition, boolean isOwned, boolean isTradeable, User user) {
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradeable;
        this.user = user;
    }

    //  bookOwned Type Constructor
    public OwnedBook(String bookCondition, boolean isOwned, boolean isTradeable, Type type) {
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradeable;
        this.type = type;
    }

    //  ALL CONSTRUCTOR
    public OwnedBook(long id, String bookCondition, boolean isOwned, boolean isTradeable, User user, Type type, Book bookOwned) {
        this.id = id;
        this.bookCondition = bookCondition;
        this.isOwned = isOwned;
        this.isTradeable = isTradeable;
        this.user = user;
        this.type = type;
        this.bookOwned = bookOwned;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBookCondition() {
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
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
