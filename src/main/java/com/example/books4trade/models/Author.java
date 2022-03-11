package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "authors")
public class Author {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please enter an author's name.")
    @Size(max = 255, message = "")
    private String fullname;

    //  1:n relationship with Book Model
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Book> books;

    //  CONSTRUCTORS
    public Author() {}

    public Author(String fullname) {
        this.fullname = fullname;
    }

    public Author(long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    //  BookAuthor Constructors
    public Author(String fullname, List<Book> books) {
        this.fullname = fullname;
        this.books = books;
    }

    public Author(long id, String fullname, List<Book> books) {
        this.id = id;
        this.fullname = fullname;
        this.books = books;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
