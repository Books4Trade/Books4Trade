package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gradeLevels")
public class GradeLevel {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Please enter a grade level")
    private int grade;

    //  CONSTRUCTORS
    public GradeLevel() {}

    public GradeLevel(int grade) {
        this.grade = grade;
    }

    public GradeLevel(long id, int grade) {
        this.id = id;
        this.grade = grade;
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
}
