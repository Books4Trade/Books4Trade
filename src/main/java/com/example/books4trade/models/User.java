package com.example.books4trade.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name="users")
public class User {
    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private String location;

    @Column
    private boolean enabled;

    // RELATIONSHIPS
    //  1:n relationship with owned_books
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OwnedBook> ownedBooks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="user")
    private List<BookReview> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="toUser")
    private List<Notification> notifications;

    //Below is for reads_book @charles
    @ManyToMany(mappedBy = "user")
    private List<Book> booksread;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

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

    // CONSTRUCTORS
    public User(){}
    public User(User copy){
        id = copy.id;
        enabled = copy.enabled;
        roles = copy.roles;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        firstName = copy.firstName;
        lastName = copy.lastName;
        location = copy.location;
        reviews = copy.reviews;
        notifications = copy.notifications;
        booksread = copy.booksread;
        watchlistBooks = copy.watchlistBooks;
    }
    public User(boolean enabled, String username, String password, String firstName, String lastName, String email, String location) {
        this.enabled = enabled;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
    }
    public User(long id, boolean enabled, String username, String password, String firstName, String lastName, String email, String location) {
        this.id = id;
        this.enabled = enabled;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
    }
    //  CONSTRUCTOR for All relationships
    public User(long id, String username, String password, String firstName, String lastName, String email, String location, List<Role> roles, List<BookReview> reviews, List<Notification> notifications, List<Book> booksread, List<Book> watchlistBooks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.roles = roles;
        this.reviews = reviews;
        this.notifications = notifications;
        this.booksread = booksread;
        this.watchlistBooks = watchlistBooks;
    }

    // Relationship Getter/Setter Methods
    public List<BookReview> getReviews(){
        return reviews;
    }
    public void setReviews(List<BookReview> reviews){ this.reviews = reviews;}
    public List<Notification> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<Notification> notifications){ this.notifications = notifications;}
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
    public List<Book> getBooksread(){ return booksread;}
    public void setBooksread(List<Book> booksread){ this.booksread = booksread;}

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    /*public void addRole(Role role){
        this.roles = getRoles();
        this.roles.add(role);
    }
    public void removeRole(Role role){
        this.roles = getRoles();
        this.roles.remove(role);
    }*/

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
    public Boolean getEnabled(){ return enabled;}
    public void setEnabled(Boolean enabled){ this.enabled = enabled;}
}
