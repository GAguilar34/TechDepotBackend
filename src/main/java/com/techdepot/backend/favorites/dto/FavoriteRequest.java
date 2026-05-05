package com.techdepot.backend.favorites.dto;

public class FavoriteRequest {
    private Long customerId;
    private Long productId;

    public FavoriteRequest() {}

    public Long getCustomerId() { return customerId; }
    public Long getProductId() { return productId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setProductId(Long productId) { this.productId = productId; }
}