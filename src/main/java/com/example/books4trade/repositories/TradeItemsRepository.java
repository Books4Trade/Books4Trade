package com.example.books4trade.repositories;

import com.example.books4trade.models.Trade;
import com.example.books4trade.models.TradeItem;
import com.example.books4trade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeItemsRepository extends JpaRepository <TradeItem, Long> {

    @Query("from TradeItem t where t.user = ?1")
    List<TradeItem> findTradeItemsByUser(User user);

    @Query("from TradeItem t where t.user = ?1 and t.confirm_sent = false")
    List<TradeItem> findTradeItemsByNotSentByUser(User user);

    TradeItem findById(long id);
}
