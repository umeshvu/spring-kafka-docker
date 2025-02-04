package com.example.demo.scheduler;

import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonProcessingScheduler {

    private final TransactionService transactionService;

    private final KafkaProducerService kafkaProducerService;

    public JsonProcessingScheduler(TransactionService transactionService, KafkaProducerService kafkaProducerService) {
        this.transactionService = transactionService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 1000)
    public void processNextEntry() {
        Map.Entry<String, Map<String, String>> entry = transactionService.getNextEntry();

        if (entry != null) {
            kafkaProducerService.sendMessage(entry.toString());
//            System.out.println("Processing entry: " + entry.getKey() + " => " + entry.getValue());
        } else {
            System.out.println("No more entries to process.");
        }
    }
}
