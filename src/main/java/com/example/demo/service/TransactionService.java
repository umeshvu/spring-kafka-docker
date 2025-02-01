package com.example.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service
public class TransactionService {

    private Map<String, Map<String, String>> jsonData;

    @PostConstruct
    public void init() throws IOException {
        readAndStoreTransactions();
    }

    public void readAndStoreTransactions() throws IOException {
        try {
            // Read JSON file from resources folder
            ObjectMapper objectMapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("sample_transaction_data.json");

            // Parse JSON file into Map<String, Map<String, String>>
            this.jsonData = objectMapper.readValue(resource.getInputStream(), new TypeReference<Map<String, Map<String, String>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    public Map<String, Map<String, String>> getAllTransactions() {
        return jsonData;
    }

    public Map.Entry<String, Map<String, String>> getNextEntry() {
        Iterator<Map.Entry<String, Map<String, String>>> iterator = jsonData.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = iterator.next();
            iterator.remove(); // Remove from map after processing
            return entry;
        }
        return null; // No more entries
    }
}
