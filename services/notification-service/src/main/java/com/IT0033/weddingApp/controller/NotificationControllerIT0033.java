package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.entity.NotificationIT0033;
import com.IT0033.weddingApp.service.NotificationServiceIT0033;
import com.IT0033.weddingApp.dto.NotificationRequestIT0033;
import com.IT0033.weddingApp.dto.NotificationResponseIT0033;
import com.IT0033.weddingApp.service.EmailServiceIT0033;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationControllerIT0033 {
    private final NotificationServiceIT0033 notificationService;
    private final EmailServiceIT0033 emailService;
    @PostMapping
    public ResponseEntity<NotificationResponseIT0033> createNotification(@Valid @RequestBody NotificationRequestIT0033 request) {
        NotificationIT0033 notification = NotificationIT0033.builder()
                .recipientEmail(request.getRecipientEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();
        NotificationIT0033 savedNotification = notificationService.createNotification(notification);
        NotificationResponseIT0033 response = mapToResponse(savedNotification);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseIT0033> getNotificationById(@PathVariable String id) {
        NotificationIT0033 notification = notificationService.getNotificationById(id);
        NotificationResponseIT0033 response = mapToResponse(notification);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseIT0033>> getNotificationsByStatus(@RequestParam(required = false) String status) {
        List<NotificationIT0033> notifications;
        if (status != null) {
            notifications = notificationService.getNotificationsByStatus(status);
        } else {
            notifications = notificationService.getNotificationsByStatus("PENDING");
        }
        List<NotificationResponseIT0033> responses = notifications.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendTestEmail() {
        try {
            // Create a test Notification object
            NotificationIT0033 notification = NotificationIT0033.builder()
                    .recipientEmail("chirantharavishka@gmail.com")  // Replace with your email
                    .subject("Test Email")
                    .message("This is a test email sent from the Notification Service.")
                    .build();

            // Save the notification to the database
            NotificationIT0033 savedNotification = notificationService.createNotification(notification);

            // Send the email using EmailService
            emailService.sendEmail(savedNotification);

            // Update the notification status to SENT
            notificationService.updateNotificationStatus(savedNotification.getId(), "SENT");

            return ResponseEntity.ok("Test email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send test email: " + e.getMessage());
        }
    }

    private NotificationResponseIT0033 mapToResponse(NotificationIT0033 notification) {
        return NotificationResponseIT0033.builder()
                .id(notification.getId())
                .recipientEmail(notification.getRecipientEmail())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .build();
    }
}
