package com.example.books4trade.repositories;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.BookReview;
import com.example.books4trade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
    List<BookReview> findByBook(Book book);
    List<BookReview> findByUser(User user);
}
