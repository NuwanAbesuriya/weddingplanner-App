package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.repository.NotificationRepositoryIT0033;
import com.IT0033.weddingApp.entity.NotificationIT0033;
import com.IT0033.weddingApp.exception.NotificationNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceIT0033 {
    private final NotificationRepositoryIT0033 notificationRepository;
    private final EmailServiceIT0033 emailService;

    public NotificationIT0033 createNotification(NotificationIT0033 notification) {
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<NotificationIT0033> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatus(status);
    }

    public NotificationIT0033 getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));
    }

    public void updateNotificationStatus(String id, String status) {
        NotificationIT0033 notification = getNotificationById(id);
        notification.setStatus(status);
        if (status.equals("SENT")) {
            notification.setSentAt(LocalDateTime.now());
        }
        notificationRepository.save(notification);
    }
}
