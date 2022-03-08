package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ownedBooks")
public class OwnedBook {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Please enter either Poor/Fair/Good/Better/New.")
    @Size(min = 3, max = 6, message = "Condition should be between 3 and 6 characters long")
    private String bookCondtion;

    @NotNull
    @AssertTrue
    private boolean isOwned;

    @NotNull
    @AssertFalse
    private boolean isTradable;

    //  need to add 1:n relationship from Users

    //  need to add 1:n relationship from Types

    //  need to add 1:n relationship with Books

    //  CONSTRUCTORS

    public OwnedBook() {}

    public OwnedBook(String bookCondtion, boolean isOwned, boolean isTradable) {
        this.bookCondtion = bookCondtion;
        this.isOwned = isOwned;
        this.isTradable = isTradable;
    }

    public OwnedBook(long id, String bookCondtion, boolean isOwned, boolean isTradable) {
        this.id = id;
        this.bookCondtion = bookCondtion;
        this.isOwned = isOwned;
        this.isTradable = isTradable;
    }

    //  GETTERS/SETTERS

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBookCondtion() {
        return bookCondtion;
    }
    public void setBookCondtion(String bookCondtion) {
        this.bookCondtion = bookCondtion;
    }
    public boolean isOwned() {
        return isOwned;
    }
    public void setOwned(boolean owned) {
        isOwned = owned;
    }
    public boolean isTradable() {
        return isTradable;
    }
    public void setTradable(boolean tradable) {
        isTradable = tradable;
    }
}
