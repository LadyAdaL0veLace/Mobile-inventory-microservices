package com.example.booking.service.client;

import com.example.booking.service.client.dto.MobileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "INVENTORY-SERVICE", configuration = com.example.booking.service.FeignConfig.class)
public interface InventoryClient {

    @GetMapping("/inventory/mobile/{id}")
    MobileDto getMobile(@PathVariable("id") Long id);

    @PatchMapping("/inventory/mobile/{id}")
    MobileDto patchMobile(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates);
}
