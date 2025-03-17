package com.IT0033.weddingApp.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducerIT0033 {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "notification_topic";

    public void sendMessage(String message) {
        log.info("Sending message to Kafka: {}", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
