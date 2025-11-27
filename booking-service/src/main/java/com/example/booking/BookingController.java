package com.example.booking;

import com.example.booking.mobile;
import com.example.booking.service.InventoryClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final InventoryClientService inventoryClient;

    public BookingController(InventoryClientService inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @GetMapping("/check-mobile/{id}")
    public ResponseEntity<mobile> checkMobile(@PathVariable Long id) {
        mobile m = inventoryClient.getMobileById(id);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/mobile/{id}")
    public ResponseEntity<mobile> getMockMobile(@PathVariable Long id) {
        mobile m = new mobile();
        m.setId(id);
        m.setBrand("MockBrand");
        m.setModel("MockModel");
        m.setColor("Black");
        m.setStorage("128GB");
        m.setPrice(BigDecimal.valueOf(50000));
        m.setQuantity(10);
        return ResponseEntity.ok(m);
    }

}

