package com.IT0033.weddingApp.kafka;

import com.IT0033.weddingApp.entity.NotificationIT0033;
import com.IT0033.weddingApp.service.EmailServiceIT0033;
import com.IT0033.weddingApp.service.NotificationServiceIT0033;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListenerIT0033 {
    private final EmailServiceIT0033 emailService;
    private final NotificationServiceIT0033 notificationService;

    @KafkaListener(topics = "notification_topic", groupId = "notification-service-group")
    public void listen(String message) {
        try {
            // Assuming the message is the notification ID
            String notificationId = message;
            NotificationIT0033 notification = notificationService.getNotificationById(notificationId);
            emailService.sendEmail(notification);
            notificationService.updateNotificationStatus(notificationId, "SENT");
        } catch (Exception e) {
            log.error("Error processing notification: {}", e.getMessage());
            // Optionally, update notification status to FAILED
            // notificationService.updateNotificationStatus(notificationId, "FAILED");
        }
    }
}
