package com.example.discount.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DiscountController {
    @GetMapping("/discount")
    public String getDiscount() {
        return "Current discount is 20% on all bookings!";
    }
}
