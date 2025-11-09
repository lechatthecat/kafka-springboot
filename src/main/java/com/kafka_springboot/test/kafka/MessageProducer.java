package com.kafka_springboot.test.kafka;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


/**
 * Kafka producer class for sending messages to the specified topic.
 */
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Inject topic name dynamically from environment
    @Value("${KAFKA_DEFAULT_TOPIC:test-topic}")
    private String defaultTopic;

    /**
     * Send message to the configured topic.
     *
     * @param message message content to send
     */
    public void sendMessage(String message) {
        kafkaTemplate.send(defaultTopic, message);
        System.out.println("📤 [KafkaProducer] Sent message: " + message);
    }

    /**
     * Send message to a specific topic (optional)
     *
     * @param topic   Kafka topic name
     * @param message message content to send
     */
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("📤 [KafkaProducer] Sent message to topic '" + topic + "': " + message);
    }
}