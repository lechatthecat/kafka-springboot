package com.kafka_springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TestApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
