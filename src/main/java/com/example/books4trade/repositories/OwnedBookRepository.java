package com.example.books4trade.repositories;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.OwnedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedBookRepository extends JpaRepository<OwnedBook, Long> {
    List<OwnedBook> findOwnedBooksByBook(Book book);
}
