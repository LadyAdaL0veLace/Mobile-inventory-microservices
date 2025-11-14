package com.example.booking.service.DTO;

public class CreateBookingRequest {

    private Long mobileId;
    private String userId;        // optional user identifier
    private String userEmail;     // required to send confirmation
    private Integer quantity;

    public CreateBookingRequest() {}

    public CreateBookingRequest(Long mobileId, String userId, String userEmail, Integer quantity) {
        this.mobileId = mobileId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.quantity = quantity;
    }

    public Long getMobileId() {
        return mobileId;
    }

    public void setMobileId(Long mobileId) {
        this.mobileId = mobileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

