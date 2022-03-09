package com.example.books4trade.models;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "categories")
public class Category {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 255)
    @Size(min = 2, max = 255, message = "MINIMUM: 2 characters, MAX: 255 characters.")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;

    //  n:m relation for user_fav_category table
    @ManyToMany(mappedBy = "favoriteCategories")
    private List<User> usersFavCategories;

    //  CONSTRUCTORS

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Category(long id, String name, List<Book> books, List<User> usersFavCategories) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.usersFavCategories = usersFavCategories;
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
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public List<User> getUsersFavCategories() {
        return usersFavCategories;
    }
    public void setUsersFavCategories(List<User> usersFavCategories) {
        this.usersFavCategories = usersFavCategories;
    }
}