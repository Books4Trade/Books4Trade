package com.example.books4trade.repositories;

import com.example.books4trade.models.TradeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeItemsRepository extends JpaRepository <TradeItem, Long> {
}
