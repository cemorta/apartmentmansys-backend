package com.myapartment.apartment_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myapartment.apartment_management.dto.MessageDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final RestTemplate restTemplate;

    public ChatController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToBot(@RequestBody MessageDTO userMessage) {
        // n8n webhook URL
        String webhookUrl = "http://localhost:5678/webhook-test/send-to-ai";

        // Prepare request body
        Map<String, String> request = new HashMap<>();
        request.put("message", userMessage.getMessage());
        request.put("userId", userMessage.getUserId().toString());


        // Send to n8n and wait for response
        ResponseEntity<String> aiResponse = restTemplate.postForEntity(webhookUrl, request, String.class);

        // Return AI's response to frontend
        return ResponseEntity.ok(aiResponse.getBody());
    }
}

