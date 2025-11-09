package com.kafka_springboot.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    // Inject KafkaTemplate for sending messages to Kafka
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Send a message to a specific Kafka topic.
     *
     * @param topic   the topic name
     * @param message the message to send
     */
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("✅ [KafkaProducer] Sent message to topic '" + topic + "': " + message);
    }
}
