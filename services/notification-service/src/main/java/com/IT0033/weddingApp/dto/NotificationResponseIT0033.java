package com.IT0033.weddingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseIT0033 {
    private String id;
    private String recipientEmail;
    private String subject;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}
