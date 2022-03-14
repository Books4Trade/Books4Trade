package com.example.books4trade.repositories;

import com.example.books4trade.models.Author;
import com.example.books4trade.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByFullname(String fullname);

    @Query("from Authors a where a.fullname like %:term%")
    List<Author> searchByFullnameLike(@Param("term") String term);
}
