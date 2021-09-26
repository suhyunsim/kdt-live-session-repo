package com.example.gccoffee.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(UUID productId, String productName, Category category, long price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public Product(UUID productId, String productName, Category category, long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setCategory(Category category) {
        this.category = category;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setPrice(long price) {
        this.price = price;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
