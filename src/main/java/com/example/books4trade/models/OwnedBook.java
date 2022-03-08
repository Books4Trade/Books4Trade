package com.example.books4trade.models;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ownedBooks")
public class OwnedBook {
    //  PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Please enter either Poor/Fair/Good/Better/New.")
    @Size(min = 3, max = 6, message = "Condition should be between 3 and 6 characters long")
    private String bookCondtion;

    @NotNull
    @AssertTrue
    private boolean isOwned;

    @NotNull
    private boolean isTradable;





}
