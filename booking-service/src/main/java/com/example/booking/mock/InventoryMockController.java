package com.example.booking.mock;

import com.example.booking.mobile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/mock-inventory")
public class InventoryMockController {

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

