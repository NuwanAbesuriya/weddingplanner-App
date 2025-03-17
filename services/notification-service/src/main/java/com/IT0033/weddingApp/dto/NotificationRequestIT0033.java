package com.IT0033.weddingApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestIT0033 {
    @NotBlank(message = "Recipient email is mandatory")
    @Email(message = "Invalid email format")
    private String recipientEmail;

    @NotBlank(message = "Subject is mandatory")
    private String subject;

    @NotBlank(message = "Message is mandatory")
    private String message;
}
