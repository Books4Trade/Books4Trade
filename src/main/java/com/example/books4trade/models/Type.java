package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "types")
public class Type {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "All books require a type: Paperback, Hardback and/or e-Book")
    private String name;

    //  1:n relationship with owned_books
    @OneToMany(mappedBy = "type")
    private List<OwnedBook> ownedBooks;

    //  CONSTRUCTORS
    public Type() {}

    public Type(String name) {
        this.name = name;
    }

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //  Relationship Constructors

    public Type(String name, List<OwnedBook> ownedBooks) {
        this.name = name;
        this.ownedBooks = ownedBooks;
    }

    //  Final All CONSTRUCTOR
    public Type(long id, String name, List<OwnedBook> ownedBooks) {
        this.id = id;
        this.name = name;
        this.ownedBooks = ownedBooks;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<OwnedBook> getOwnedBooks() {
        return ownedBooks;
    }
    public void setOwnedBooks(List<OwnedBook> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }
}
