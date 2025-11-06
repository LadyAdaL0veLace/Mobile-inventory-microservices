package com.example.Inventory.service;

import com.example.Inventory.service.service.MobileService;
import com.example.Inventory.service.model.mobile;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobile")
public class InventoryController {

    private final MobileService service;

    public InventoryController(MobileService service) {
        this.service = service;
    }

    @GetMapping("/mobile")
    public ResponseEntity<List<mobile>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/mobile/{id}")
    public ResponseEntity<mobile> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/mobile")
    public ResponseEntity<mobile> create(@RequestBody mobile mobile) {
        mobile saved = service.save(mobile);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/mobile/{id}")
    public ResponseEntity<mobile> update(@PathVariable Long id, @RequestBody mobile mobile) {
        return service.update(id, mobile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/mobile/{id}")
    public ResponseEntity<mobile> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return service.partialUpdate(id, updates)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/mobile/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

