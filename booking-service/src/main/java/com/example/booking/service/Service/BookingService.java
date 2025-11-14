package com.example.booking.service.Service;

import com.example.booking.service.client.DiscountClient;
import com.example.booking.service.client.EmailClient;
import com.example.booking.service.client.InventoryClient;
import com.example.booking.service.client.dto.MobileDto;
import com.example.booking.service.DTO.CreateBookingRequest;
import com.example.booking.service.DTO.EmailRequest;
import com.example.booking.service.Model.Booking;
import com.example.booking.service.Repository.BookingRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingService {

    public final BookingRepository bookingRepository;
    private final InventoryClient inventoryClient;
    private final DiscountClient discountClient;
    private final EmailClient emailClient;

    public BookingService(BookingRepository bookingRepository, InventoryClient inventoryClient, DiscountClient discountClient, EmailClient emailClient) {
        this.bookingRepository = bookingRepository;
        this.inventoryClient = inventoryClient;
        this.discountClient = discountClient;
        this.emailClient = emailClient;
    }

    @Transactional
    public Booking createBooking(CreateBookingRequest req) {
        // 1. Fetch mobile details from Inventory
        MobileDto mobile;
        try {
            mobile = inventoryClient.getMobile(req.getMobileId());
        } catch (FeignException e) {
            throw new RuntimeException("Inventory service unavailable or mobile not found: " + e.getMessage());
        }

        if (mobile == null) throw new RuntimeException("Mobile not found in inventory");

        // 2. Check quantity
        if (mobile.getQuantity() == null || mobile.getQuantity() < req.getQuantity()) {
            throw new RuntimeException("Insufficient quantity in inventory");
        }

        // 3. Get discount from discount service
        BigDecimal discountPercent = BigDecimal.ZERO;
        try {
            BigDecimal d = discountClient.getDiscountPercentage(mobile.getId());
            if (d != null) discountPercent = d;
        } catch (FeignException e) {
            // If discount service down, continue with 0% discount (or you may choose to fail)
            discountPercent = BigDecimal.ZERO;
        }

        // 4. Calculate final price
        BigDecimal unitPrice = mobile.getPrice();
        BigDecimal quantity = BigDecimal.valueOf(req.getQuantity());
        BigDecimal totalPrice = unitPrice.multiply(quantity);

        BigDecimal discountAmount = totalPrice.multiply(discountPercent).divide(BigDecimal.valueOf(100));
        BigDecimal finalPrice = totalPrice.subtract(discountAmount);

        // 5. Create booking entity and persist
        Booking booking = new Booking();
        booking.setUserId(req.getUserId());
        booking.setUserEmail(req.getUserEmail());
        booking.setMobileId(mobile.getId());
        booking.setMobileBrand(mobile.getBrand());
        booking.setMobileModel(mobile.getModel());
        booking.setQuantity(req.getQuantity());
        booking.setUnitPrice(unitPrice);
        booking.setDiscountPercent(discountPercent);
        booking.setFinalPrice(finalPrice);
        booking.setStatus("CREATED");
        booking.setBookingDate(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);

        // 6. Decrement inventory (call inventory patch)
        Map<String, Object> updates = new HashMap<>();
        updates.put("quantity", mobile.getQuantity() - req.getQuantity());

        try {
            inventoryClient.patchMobile(mobile.getId(), updates);
        } catch (FeignException e) {
            // Rollback transaction by throwing runtime exception so DB rollback happens
            throw new RuntimeException("Failed to update inventory after booking: " + e.getMessage());
        }

        // 7. Send confirmation email (fire-and-forget)
        try {
            String subject = "Booking Confirmation - Order #" + saved.getId();
            String body = "Dear user,\n\nYour booking has been confirmed.\n\n" +
                    "Booking ID: " + saved.getId() + "\n" +
                    "Mobile: " + saved.getMobileBrand() + " " + saved.getMobileModel() + "\n" +
                    "Quantity: " + saved.getQuantity() + "\n" +
                    "Final price: " + saved.getFinalPrice() + "\n\nThank you.";
            EmailRequest emailRequest = new EmailRequest(saved.getUserEmail(), subject, body);
            emailClient.sendEmail(emailRequest);
        } catch (FeignException e) {
            // Log, but do not rollback booking (optional behaviour)
            // you can also mark booking as 'EMAIL_FAILED' or retry later
            saved.setStatus("CONFIRMED_EMAIL_FAILED");
            bookingRepository.save(saved);
            return saved;
        }

        saved.setStatus("CONFIRMED");
        bookingRepository.save(saved);
        return saved;
    }
}

