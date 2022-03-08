package com.example.books4trade.models;

import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public UserRole(){}
    public UserRole(long id, String name){
        this.id = id;
        this.name = name;
    }

}
