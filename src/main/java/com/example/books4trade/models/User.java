package com.example.books4trade.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)

    // RELATIONSHIPS
    @JoinColumn(name = "role_id")
    private long role;

    @OneToMany(mappedBy = "user")
    private List<BookReview> reviews;

    @OneToMany(mappedBy = "toUser")
    private List<Notification> notifications;

    //Below is for reads_book @charles
    @ManyToMany(mappedBy = "user")
    private List<Book> books;

    //  n:m relation for watchlist table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_book_watchlist",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> watchlistBooks;

    //  n:m relation for user_fav_category table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_fav_category",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> favoriteCategories;

    //  1:n relationship with owned_books
    @OneToMany(mappedBy = "user")
    private List<OwnedBook> ownedBooks;

    // CONSTRUCTORS
    public User(){}
    public User(User copy){
        id = copy.id;
        role = copy.role;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        firstName = copy.firstName;
        lastName = copy.lastName;
        location = copy.location;
        reviews = copy.reviews;
        notifications = copy.notifications;
    }
    public User(long id, long role, String username, String password, String firstName, String lastName, String email, String location, List<BookReview> reviews, List<Notification> notifications) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.reviews = reviews;
        this.notifications = notifications;
    }
    //  CONSTRUCTOR for Watchlist relationship
    public User(long id, String username, String password, String firstName, String lastName, String email, String location, long role, List<BookReview> reviews, List<Notification> notifications, List<Book> books, List<Book> watchlistBooks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.role = role;
        this.reviews = reviews;
        this.notifications = notifications;
        this.books = books;
        this.watchlistBooks = watchlistBooks;
    }

    //  CONSTRUCTOR for OwnedBook relationship
    public User(String username, String password, String firstName, String lastName, String email, String location, long role, List<OwnedBook> ownedBooks) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.role = role;
        this.ownedBooks = ownedBooks;
    }
    public User(long id, String username, String password, String firstName, String lastName, String email, String location, long role, List<OwnedBook> ownedBooks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.role = role;
        this.ownedBooks = ownedBooks;
    }


    // Relationship Getter/Setter Methods
    public List<BookReview> getReviews(){
        return reviews;
    }
    public List<Notification> getNotifications() {
        return notifications;
    }
    public List<Book> getWatchlistBooks() {
        return watchlistBooks;
    }
    public void setWatchlistBooks(List<Book> watchlistBooks) {
        this.watchlistBooks = watchlistBooks;
    }
    public List<OwnedBook> getOwnedBooks() {
        return ownedBooks;
    }
    public void setOwnedBooks(List<OwnedBook> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    // User Field Getters & Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public long getRole() {
        return role;
    }
    public void setRole(long role) {
        this.role = role;
    }
}
