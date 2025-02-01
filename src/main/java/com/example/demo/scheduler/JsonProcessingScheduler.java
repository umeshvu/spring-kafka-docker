package com.example.demo.scheduler;

import com.example.demo.service.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonProcessingScheduler {

    private final TransactionService transactionService;

    public JsonProcessingScheduler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Scheduled(fixedRate = 300)
    public void processNextEntry() {
        Map.Entry<String, Map<String, String>> entry = transactionService.getNextEntry();

        if (entry != null) {
            System.out.println("Processing entry: " + entry.getKey() + " => " + entry.getValue());
        } else {
            System.out.println("No more entries to process.");
        }
    }
}
