package com.example.booking.service.client.dto;

import java.math.BigDecimal;

public class MobileDto {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String storage;
    private BigDecimal price;
    private Integer quantity;

    public MobileDto() {}

    public MobileDto(Long id, String brand, String model, String color, String storage, BigDecimal price, Integer quantity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.quantity = quantity;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getStorage() { return storage; }
    public void setStorage(String storage) { this.storage = storage; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}

