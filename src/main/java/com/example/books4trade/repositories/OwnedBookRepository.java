package com.example.books4trade.repositories;

import com.example.books4trade.models.OwnedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedBookRepository extends JpaRepository<OwnedBook, Long> {
}
