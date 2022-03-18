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

    @Column(nullable = false)
    private boolean isTradeable;

    //  need to add 1:n relationship from Users
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    //  need to add 1:n relationship with Books
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    //  need to add 1:n relationship from Types
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;



    //  CONSTRUCTORS
    public OwnedBook() {}

    //  ALL Except ID
    public OwnedBook(String bookCondition, boolean isTradeable, Type type, User user, Book book) {
        this.bookCondition = bookCondition;
        this.isTradeable = isTradeable;
        this.type = type;
        this.user = user;
        this.book = book;
    }

    //  ALL CONSTRUCTOR
    public OwnedBook(long id, String bookCondition, boolean isTradeable, User user, Type type, Book book) {
        this.id = id;
        this.bookCondition = bookCondition;
        this.isTradeable = isTradeable;
        this.user = user;
        this.type = type;
        this.book = book;
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
    public boolean isTradable() {
        return isTradeable;
    }
    public void setTradable(boolean tradable) {
        isTradeable = tradable;
    }
    public Book getBook() {
        return book;
    }
    public void setBookOwned(Book book) {
        this.book = book;
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
