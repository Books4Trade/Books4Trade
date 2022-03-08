package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "types")
public class Type {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "All books require a type: Paperback, Hardback and/or e-Book")
    private String name;

    //  CONSTRUCTORS
    public Type() {}

    public Type(String name) {
        this.name = name;
    }

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
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
}
