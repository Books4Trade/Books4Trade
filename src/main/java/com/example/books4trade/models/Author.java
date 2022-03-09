package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name = "authors")
public class Author {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please enter an author's name.")
    @Size(min = 2, max = 255, message = "")
    private String fullname;

    //  CONSTRUCTORS
    public Author() {}

    public Author(String fullname) {
        this.fullname = fullname;
    }

    public Author(long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
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
}
