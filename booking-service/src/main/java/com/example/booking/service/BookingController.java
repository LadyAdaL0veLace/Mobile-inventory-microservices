package com.example.booking.service;

import com.example.booking.service.DTO.CreateBookingRequest;
import com.example.booking.service.Model.Booking;
import com.example.booking.service.Service.BookingService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request) {
        try {
            Booking booking = bookingService.createBooking(request);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            // return meaningful message
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    // Optional: endpoints for get bookings
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return bookingService.bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


