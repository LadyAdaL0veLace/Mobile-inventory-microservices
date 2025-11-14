package com.example.booking.service.client;

import com.example.booking.service.DTO.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "EMAIL-SERVICE", configuration = com.example.booking.service.FeignConfig.class)
public interface EmailClient {

    @PostMapping("/email/send")
    void sendEmail(@RequestBody EmailRequest request);
}

