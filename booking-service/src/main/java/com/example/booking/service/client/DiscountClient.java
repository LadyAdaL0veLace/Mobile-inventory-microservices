package com.example.booking.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "DISCOUNT-SERVICE", configuration = com.example.booking.service.FeignConfig.class)
public interface DiscountClient {

    @GetMapping("/discount/percentage/{mobileId}")
    BigDecimal getDiscountPercentage(@PathVariable("mobileId") Long mobileId);
}

