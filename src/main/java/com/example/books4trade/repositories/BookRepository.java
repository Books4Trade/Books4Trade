package com.example.books4trade.repositories;

import com.example.books4trade.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Books b where b.title like %:term%")
    List<Book> searchByTitleLike(@Param("term") String term);

    List<Book> searchByAuthorId(long id);
}
