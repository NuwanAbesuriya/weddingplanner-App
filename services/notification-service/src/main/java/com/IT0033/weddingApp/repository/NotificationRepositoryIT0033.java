package com.IT0033.weddingApp.repository;

import com.IT0033.weddingApp.entity.NotificationIT0033;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepositoryIT0033 extends MongoRepository<NotificationIT0033, String> {
    List<NotificationIT0033> findByStatus(String status);
}
