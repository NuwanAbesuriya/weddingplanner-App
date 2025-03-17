package com.IT0033.weddingApp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class KafkaConfigIT0033 {

    @Bean
    public ProducerFactory<String, String> producerFactory(org.springframework.core.env.Environment env) {
        String bootstrapServers = env.getProperty("spring.kafka.bootstrap-servers");
        if (bootstrapServers == null || bootstrapServers.isEmpty()) {
            log.error("spring.kafka.bootstrap-servers property is not set or empty");
            throw new IllegalArgumentException("spring.kafka.bootstrap-servers property is not set or empty");
        }

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Add any additional producer properties if needed

        log.debug("Kafka Producer Configuration: {}", configProps);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(org.springframework.core.env.Environment env) {
        return new KafkaTemplate<>(producerFactory(env));
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(org.springframework.core.env.Environment env) {
        String bootstrapServers = env.getProperty("spring.kafka.bootstrap-servers");
        String groupId = env.getProperty("spring.kafka.consumer.group-id");
        if (bootstrapServers == null || bootstrapServers.isEmpty()) {
            log.error("spring.kafka.bootstrap-servers property is not set or empty");
            throw new IllegalArgumentException("spring.kafka.bootstrap-servers property is not set or empty");
        }
        if (groupId == null || groupId.isEmpty()) {
            log.error("spring.kafka.consumer.group-id property is not set or empty");
            throw new IllegalArgumentException("spring.kafka.consumer.group-id property is not set or empty");
        }

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Add any additional consumer properties if needed

        log.debug("Kafka Consumer Configuration: {}", props);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(org.springframework.core.env.Environment env) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(env));
        return factory;
    }
}
