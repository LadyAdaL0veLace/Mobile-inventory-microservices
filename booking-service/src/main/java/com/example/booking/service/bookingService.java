package com.example.booking.service;

import com.example.booking.mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bookingService {

    @Autowired
    private InventoryClientService inventoryClientService;


    public mobile fetchMobileForBooking(Long mobileId) {

        // Call Inventory Client Service
        mobile m = inventoryClientService.getMobileById(mobileId);

        if (m == null) {
            throw new RuntimeException("Mobile not found in inventory with ID: " + mobileId);
        }

        return m;
    }

    /**
     * (for future)
     */
    public String bookMobile(Long mobileId) {
        mobile m = fetchMobileForBooking(mobileId);

        if (m.getQuantity() <= 0) {
            return "Mobile is out of stock!";
        }

        return "Booking successful for: " + m.getBrand() + " " + m.getModel();
    }
}

