package com.kafka_springboot.test.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import org.springframework.beans.factory.annotation.Value;

/**
 * Kafka consumer that listens to messages from a configured topic.
 */
@Component
public class MessageConsumer {

    // Inject topic and groupId from environment
    @Value("${KAFKA_DEFAULT_TOPIC:test-topic}")
    private String topic;

    @Value("${KAFKA_CONSUMER_GROUP:test-group}")
    private String groupId;

    /**
     * Listen to Kafka messages dynamically from configured topic and groupId.
     * Note: The topic/groupId placeholders must match your application.properties or environment variables.
     *
     * @param message received Kafka message
     */
    @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.groupId}")
    public void listen(String message) {
        System.out.println("📥 [KafkaConsumer] Received message: " + message);
    }
}