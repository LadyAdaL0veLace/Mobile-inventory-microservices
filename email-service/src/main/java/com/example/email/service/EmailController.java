package com.example.email.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmailController {
    @GetMapping("/email")
    public String sendEmail() {
        return "Email sent successfully!";
    }
}
