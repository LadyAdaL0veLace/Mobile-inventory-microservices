package com.example.Inventory.service.repository;

import com.example.Inventory.service.model.mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MobileRepository extends JpaRepository<mobile, Long> {
    Optional<mobile> findByBrandAndModel(String brand, String model);
}
