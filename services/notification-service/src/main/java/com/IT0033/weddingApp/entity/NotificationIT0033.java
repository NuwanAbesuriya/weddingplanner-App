package com.IT0033.weddingApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
public class NotificationIT0033 {

    @Id
    private String id;

    private String recipientEmail;
    private String subject;
    private String message;
    private String status; // e.g., SENT, FAILED
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}
