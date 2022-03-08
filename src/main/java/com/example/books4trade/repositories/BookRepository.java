package com.example.books4trade.repositories;

import com.example.books4trade.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
