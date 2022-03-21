package com.example.books4trade.models;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean isLiked;

    @ManyToOne
    @JoinColumn (name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "reviews_id")
    private BookReview reviews;

    //  CONSTRUCTORS
    public Like() {
    }

    public Like(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Like(User user, BookReview reviews) {
        this.user = user;
        this.reviews = reviews;
    }

    public Like(boolean isLiked, User user, BookReview reviews) {
        this.isLiked = isLiked;
        this.user = user;
        this.reviews = reviews;
    }

    public Like(long id, boolean isLiked, User user, BookReview reviews) {
        this.id = id;
        this.isLiked = isLiked;
        this.user = user;
        this.reviews = reviews;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean isLiked() {
        return isLiked;
    }
    public void setLiked(boolean liked) {
        isLiked = liked;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public BookReview getReviews() {
        return reviews;
    }
    public void setReviews(BookReview reviews) {
        this.reviews = reviews;
    }
}