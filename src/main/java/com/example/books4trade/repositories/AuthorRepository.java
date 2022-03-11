package com.example.books4trade.repositories;

import com.example.books4trade.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByFullname(String fullname);
}
