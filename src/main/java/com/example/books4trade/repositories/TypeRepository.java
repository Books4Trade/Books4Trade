package com.example.books4trade.repositories;

import com.example.books4trade.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository <Type, Long> {
    Type findById(long id);
}
