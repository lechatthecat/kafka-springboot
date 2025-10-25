package com.kafka_springboot.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.data.redis.password:}")
    private String password;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration clusterConfig =
                new RedisClusterConfiguration(Arrays.asList(clusterNodes.split(",")));

        if (password != null && !password.isBlank()) {
            clusterConfig.setPassword(password);
        }

        return new LettuceConnectionFactory(clusterConfig);
    }

    @Bean
    public StringRedisTemplate redisTemplate(LettuceConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }
}