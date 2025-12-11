package com.example.Inventory.service.service;

import com.example.Inventory.service.model.mobile;
import com.example.Inventory.service.repository.MobileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final MobileRepository mobileRepo;

    public InventoryService(MobileRepository mobileRepo) {
        this.mobileRepo = mobileRepo;
    }

    public mobile getMobile(Long id) {
        return mobileRepo.findById(id).orElse(null);
    }

    public mobile addMobile(mobile mobile) {
        return mobileRepo.save(mobile);
    }

    public mobile updateQuantity(Long id, int qty) {
        mobile m = mobileRepo.findById(id).orElse(null);
        if (m == null) return null;

        m.setQuantity(qty);
        return mobileRepo.save(m);
    }

    public boolean deleteMobile(Long id) {
        if (!mobileRepo.existsById(id)) return false;
        mobileRepo.deleteById(id);
        return true;
    }
}

