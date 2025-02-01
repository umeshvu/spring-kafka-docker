package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/json")
public class JsonController {

    private final TransactionService transactionService;

    public JsonController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/data")
    public Map<String, Map<String, String>> getJsonData() {
        return transactionService.getAllTransactions();
    }
}
