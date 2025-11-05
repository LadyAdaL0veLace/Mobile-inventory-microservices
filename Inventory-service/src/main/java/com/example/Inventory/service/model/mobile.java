package com.example.Inventory.service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mobile")

public class mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String color;
    private String storage;
    private BigDecimal price;
    private Integer quantity;

    // Default constructor
    public mobile() {}

    // All-args constructor
    public mobile(Long id, String brand, String model, String color, String storage, BigDecimal price, Integer quantity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.quantity = quantity;
    }

    // Constructor without ID
    public mobile(String brand, String model, String color, String storage, BigDecimal price, Integer quantity) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // toString()
    @Override
    public String toString() {
        return "Mobile{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", storage='" + storage + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}


