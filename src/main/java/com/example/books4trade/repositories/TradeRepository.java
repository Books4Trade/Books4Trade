package com.example.books4trade.repositories;

import com.example.books4trade.models.Trade;
import com.example.books4trade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findAll();

    @Query("from Trade t where t.item1.user = ?1")
    List<Trade> findTradeByUser(User user);
}
