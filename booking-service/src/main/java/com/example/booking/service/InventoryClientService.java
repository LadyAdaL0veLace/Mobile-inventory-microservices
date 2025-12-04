package com.example.booking.service;

import com.example.booking.mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClientService {

    @Autowired
    private RestTemplate restTemplate;

    // For Phase A: calling mock inventory endpoint from booking-service
    private final String inventoryBase = "http://localhost:8087/mock-inventory";

    public mobile getMobileById(Long id) {
        String url = inventoryBase + "/mobile/" + id;
        return restTemplate.getForObject(url, mobile.class);
    }
}
