package com.example.Inventory.service.service;

import com.example.Inventory.service.model.mobile;
import com.example.Inventory.service.repository.MobileRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MobileService {

    private final MobileRepository repository;

    public MobileService(MobileRepository repository) {
        this.repository = repository;
    }

    public List<mobile> getAll() {
        return repository.findAll();
    }

    public Optional<mobile> getById(Long id) {
        return repository.findById(id);
    }

    public mobile save(mobile mobile) {
        return repository.save(mobile);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<mobile> update(Long id, mobile updated) {
        return repository.findById(id).map(existing -> {
            updated.setId(existing.getId());
            return repository.save(updated);
        });
    }

    public Optional<mobile> partialUpdate(Long id, Map<String, Object> updates) {
        return repository.findById(id).map(mobile -> {
            applyUpdates(mobile, updates);
            return repository.save(mobile);
        });
    }

    private void applyUpdates(mobile mobile, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            switch (key) {
                case "brand" -> mobile.setBrand((String) value);
                case "model" -> mobile.setModel((String) value);
                case "color" -> mobile.setColor((String) value);
                case "storage" -> mobile.setStorage((String) value);
                case "price" -> mobile.setPrice(new BigDecimal(value.toString()));
                case "quantity" -> mobile.setQuantity(Integer.valueOf(value.toString()));
                default -> {
                }
            }
        });
    }
}

