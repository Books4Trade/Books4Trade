package com.example.books4trade.models;

import javax.persistence.*;

@Entity
@Table(name="notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Boolean hasRead;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;



}
