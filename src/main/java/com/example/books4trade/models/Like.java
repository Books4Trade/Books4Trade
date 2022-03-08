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

    public Like() {
    }

    public Like(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Like(long id, boolean isLiked) {
        this.id = id;
        this.isLiked = isLiked;
    }

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
}