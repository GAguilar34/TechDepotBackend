package com.techdepot.backend.product.rating.dto;

import com.techdepot.backend.product.rating.model.ProductRating;
import java.time.LocalDateTime;

public class ProductRatingDTO {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long productId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductRatingDTO(ProductRating productRating) {
        this.id = productRating.getId();
        this.customerId = productRating.getCustomer().getId();
        this.customerName = productRating.getCustomer().getName();
        this.productId = productRating.getProduct().getId();
        this.rating = productRating.getRating();
        this.comment = productRating.getComment();
        this.createdAt = productRating.getCreatedAt();
        this.updatedAt = productRating.getUpdatedAt();
    }

    public Long getId() {return id;}
    public Long getCustomerId() {return customerId;}
    public String getCustomerName() {return customerName;}
    public Long getProductId() {return productId;}
    public int getRating() {return rating;}
    public String getComment() {return comment;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}
}
