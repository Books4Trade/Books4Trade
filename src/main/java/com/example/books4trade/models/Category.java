package com.example.books4trade.models;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 255)
    @Size(min = 2, max = 255, message = "MINIMUM: 2 characters, MAX: 255 characters.")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}