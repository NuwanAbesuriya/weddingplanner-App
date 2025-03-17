package com.IT0033.weddingApp.service;


import com.IT0033.weddingApp.entity.NotificationIT0033;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceIT0033 {
    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;

    public void sendEmail(NotificationIT0033 notification) throws MessagingException, IOException, TemplateException {
        // Create a MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // Use MimeMessageHelper to set up the message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(notification.getRecipientEmail());
        helper.setSubject(notification.getSubject());

        // Prepare email content using FreeMarker template
        Map<String, Object> model = new HashMap<>();
        model.put("name", extractNameFromEmail(notification.getRecipientEmail()));
        model.put("message", notification.getMessage());

        Template template = freemarkerConfig.getTemplate("email-template.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(html, true);

        // Send the email using MimeMessage
        mailSender.send(mimeMessage);
        log.info("Email sent to {}", notification.getRecipientEmail());
    }

    private String extractNameFromEmail(String email) {
        // Simple extraction, assuming email format is name@example.com
        String namePart = email.split("@")[0];
        return namePart.substring(0, 1).toUpperCase() + namePart.substring(1);
    }
}
