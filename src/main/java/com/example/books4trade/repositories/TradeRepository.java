package com.example.books4trade.repositories;

import com.example.books4trade.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
