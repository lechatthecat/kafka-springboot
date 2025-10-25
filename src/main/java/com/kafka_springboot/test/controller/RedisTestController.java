package com.kafka_springboot.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/redis/set")
    public String set() {
        redisTemplate.opsForValue().set("hello", "world");
        return "SET OK";
    }

    @GetMapping("/redis/get")
    public String get() {
        return redisTemplate.opsForValue().get("hello");
    }
}