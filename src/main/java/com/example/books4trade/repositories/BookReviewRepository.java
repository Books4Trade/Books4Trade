package com.example.books4trade.repositories;

import com.example.books4trade.models.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReview, Long> {

}
