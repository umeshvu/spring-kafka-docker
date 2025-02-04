package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "testFailedTransactions", groupId = "my-group-id")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
