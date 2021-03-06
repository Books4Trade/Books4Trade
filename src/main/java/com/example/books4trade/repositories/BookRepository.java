package com.example.books4trade.repositories;

import com.example.books4trade.models.Author;
import com.example.books4trade.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findById(long id);

    @Query("from Book b where b.title like %:term%")
    List<Book> searchByTitleLike(@Param("term") String term);

    List<Book> searchByAuthor(Author author);
}
