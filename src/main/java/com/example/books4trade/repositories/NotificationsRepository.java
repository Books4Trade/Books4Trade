package com.example.books4trade.repositories;

import com.example.books4trade.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {

}
