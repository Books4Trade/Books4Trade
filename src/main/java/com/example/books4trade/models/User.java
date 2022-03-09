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

    @OneToMany(mappedBy = "userA")
    private List<Trade> trades;

    @OneToMany(mappedBy = "userB")
    private List<Trade> tradesB;

    //Below is for reads_book @charles
    @ManyToMany(mappedBy = "user")
    private List<Book> books;


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
    // Relationship Getter/Setter Methods
    public List<BookReview> getReviews(){
        return reviews;
    }
    public List<Notification> getNotifications() {
        return notifications;
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
