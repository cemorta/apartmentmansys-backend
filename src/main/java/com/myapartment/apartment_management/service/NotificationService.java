package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.dto.MaintenanceRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    public void sendToN8nWebhook(MaintenanceRequestDTO dto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MaintenanceRequestDTO> request = new HttpEntity<>(dto, headers);

        String n8nWebhookUrl = "http://localhost:5678/webhook/maintenance-created";

        restTemplate.postForEntity(n8nWebhookUrl, request, String.class);
    }
}
