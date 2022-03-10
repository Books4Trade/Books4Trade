package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "gradeLevels")
public class GradeLevel {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Please enter a grade level")
    private int grade;

    //  1:n relationship with books
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Book> books;

    //  CONSTRUCTORS
    public GradeLevel() {}

    public GradeLevel(int grade) {
        this.grade = grade;
    }

    public GradeLevel(long id, int grade) {
        this.id = id;
        this.grade = grade;
    }
    //  relational Constructors

    public GradeLevel(int grade, List<Book> books) {
        this.grade = grade;
        this.books = books;
    }

    public GradeLevel(long id, int grade, List<Book> books) {
        this.id = id;
        this.grade = grade;
        this.books = books;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
