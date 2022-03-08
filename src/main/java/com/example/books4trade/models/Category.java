package com.example.books4trade.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 255)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}