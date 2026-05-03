package com.techdepot.backend.order.dto;

public class OrderItemRequest {

    private Long productId;
    private int amount;

    public OrderItemRequest(){}

    // Getters
    public Long getProductId() { return productId; }
    public int getAmount() { return amount; }

    // Setters
    public void setProductId(Long productId) { this.productId = productId; }
    public void setAmount(int amount) { this.amount = amount; }
}