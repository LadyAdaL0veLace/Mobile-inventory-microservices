package com.example.Inventory.service;

import com.example.Inventory.service.model.mobile;
import com.example.Inventory.service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory/mobile")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMobile(@PathVariable Long id) {
        mobile m = inventoryService.getMobile(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    // ADD NEW MOBILE (POST)
    @PostMapping
    public ResponseEntity<mobile> addMobile(@RequestBody mobile mobile) {
        mobile saved = inventoryService.addMobile(mobile);
        return ResponseEntity.ok(saved);
    }

    // UPDATE QUANTITY ONLY (PATCH)
    @PatchMapping("/{id}/quantity/{qty}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long id, @PathVariable int qty) {
        mobile updated = inventoryService.updateQuantity(id, qty);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE MOBILE (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMobile(@PathVariable Long id) {
        boolean deleted = inventoryService.deleteMobile(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Deleted mobile with ID " + id);
    }
}
