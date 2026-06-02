package com.techdepot.backend.product.rating.dto;

public class ProductRatingRequest {

    private Long customerId;
    private int rating;
    private String comment;

    public Long getCustomerId() {return customerId;}
    public int getRating() {return rating;}
    public String getComment() {return comment;}

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
