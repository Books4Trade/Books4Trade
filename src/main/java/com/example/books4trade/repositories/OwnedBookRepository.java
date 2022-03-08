package com.example.books4trade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedBookRepository extends JpaRepository<OwnedBook, Long> {
}
