package com.example.books4trade.models;

import javax.persistence.*;

@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Boolean hasRead;

    @Column(nullable = false)
    private String body;

    @Column
    private long fromUser;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User toUser;

    // Constructors
    public Notification(){}
    public Notification(long id, String body, long fromUser, User toUser) {
        this.id = id;
        this.hasRead = false;
        this.body = body;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Boolean getHasRead() {
        return hasRead;
    }
    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Long getFromUser() {
        return fromUser;
    }
    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }
    public User getToUser() {
        return toUser;
    }
    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
