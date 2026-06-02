package com.techdepot.backend.product.rating.controller;

import com.techdepot.backend.product.rating.dto.ProductRatingDTO;
import com.techdepot.backend.product.rating.dto.ProductRatingRequest;
import com.techdepot.backend.product.rating.dto.ProductRatingSummary;
import com.techdepot.backend.product.rating.service.ProductRatingService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/{productId}/ratings")
@CrossOrigin
public class ProductRatingController {

    private final ProductRatingService productRatingService;

    public ProductRatingController(ProductRatingService productRatingService) {
        this.productRatingService = productRatingService;
    }

    @PostMapping
    public ResponseEntity<?> rateProduct(@PathVariable Long productId,
            @RequestBody ProductRatingRequest request) {
        try {
            ProductRatingDTO productRating = productRatingService.rateProduct(productId, request);
            return ResponseEntity.ok(productRating);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ProductRatingDTO> getProductRatings(@PathVariable Long productId) {
        return productRatingService.getProductRatings(productId);
    }

    @GetMapping("/summary")
    public ProductRatingSummary getProductRatingSummary(@PathVariable Long productId) {
        return productRatingService.getProductRatingSummary(productId);
    }
}
