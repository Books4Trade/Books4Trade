package com.example.books4trade.repositories;

import com.example.books4trade.models.BookReview;
import com.example.books4trade.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    //  finding likes by reviews to compare current user and liked user
    List<Like> findLikesByReviews(BookReview bookReview);
}
