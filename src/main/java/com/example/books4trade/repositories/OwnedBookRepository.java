package com.example.books4trade.repositories;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnedBookRepository extends JpaRepository<OwnedBook, Long> {
    OwnedBook findById(long id);

    List<OwnedBook> findOwnedBooksByBook(Book book);

    //  custom query to get distinct(single, no duplicates) tradable books
    @Query("SELECT DISTINCT b.book FROM OwnedBook b WHERE b.isTradeable = true")
    List<Book> findAllTradable();

    @Query("from OwnedBook b where b.user = ?1 and b.isTradeable = true")
    List<OwnedBook> findUserTradableBooks(User user);


}
