package com.techdepot.backend.product.rating.dto;

public class ProductRatingSummary {

    private double averageRating;
    private long totalRatings;

    public ProductRatingSummary(double averageRating, long totalRatings) {
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
    }

    public double getAverageRating() {return averageRating;}
    public long getTotalRatings() {return totalRatings;}
}
