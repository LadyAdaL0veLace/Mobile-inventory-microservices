package com.example.booking.service.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userEmail;

    private Long mobileId;
    private String mobileBrand;
    private String mobileModel;

    private Integer quantity;

    private BigDecimal unitPrice;
    private BigDecimal discountPercent;
    private BigDecimal finalPrice;

    private String status; // e.g., CREATED, CONFIRMED, FAILED, CANCELLED

    private LocalDateTime bookingDate;

    public Booking() {}

    public Booking(Long id, String userId, String userEmail, Long mobileId, String mobileBrand, String mobileModel, Integer quantity, BigDecimal unitPrice, BigDecimal discountPercent, BigDecimal finalPrice, String status, LocalDateTime bookingDate) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.mobileId = mobileId;
        this.mobileBrand = mobileBrand;
        this.mobileModel = mobileModel;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountPercent = discountPercent;
        this.finalPrice = finalPrice;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    // getters and setters for all fields

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Long getMobileId() { return mobileId; }
    public void setMobileId(Long mobileId) { this.mobileId = mobileId; }

    public String getMobileBrand() { return mobileBrand; }
    public void setMobileBrand(String mobileBrand) { this.mobileBrand = mobileBrand; }

    public String getMobileModel() { return mobileModel; }
    public void setMobileModel(String mobileModel) { this.mobileModel = mobileModel; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public BigDecimal getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(BigDecimal discountPercent) { this.discountPercent = discountPercent; }

    public BigDecimal getFinalPrice() { return finalPrice; }
    public void setFinalPrice(BigDecimal finalPrice) { this.finalPrice = finalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", mobileId=" + mobileId +
                ", mobileBrand='" + mobileBrand + '\'' +
                ", mobileModel='" + mobileModel + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discountPercent=" + discountPercent +
                ", finalPrice=" + finalPrice +
                ", status='" + status + '\'' +
                ", bookingDate=" + bookingDate +
                '}';
    }
}

