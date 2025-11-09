package com.kafka_springboot.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka_springboot.test.service.KafkaProducerService;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducerService kafkaProducerService;

    /**
     * Send a message to Kafka via HTTP GET request.
     * Example:
     *   GET /kafka/send?topic=test-topic&msg=hello
     */
    @GetMapping("/kafka/send")
    public String sendMessage(@RequestParam(defaultValue = "test-topic") String topic,
                              @RequestParam(defaultValue = "hello") String msg) {
        kafkaProducerService.sendMessage(topic, msg);
        return "Message sent: " + msg;
    }
}
