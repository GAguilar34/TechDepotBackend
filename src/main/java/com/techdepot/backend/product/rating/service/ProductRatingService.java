package com.techdepot.backend.product.rating.service;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.repository.CustomerRepository;
import com.techdepot.backend.product.model.Product;
import com.techdepot.backend.product.repository.ProductRepository;
import com.techdepot.backend.product.rating.dto.ProductRatingDTO;
import com.techdepot.backend.product.rating.dto.ProductRatingRequest;
import com.techdepot.backend.product.rating.dto.ProductRatingSummary;
import com.techdepot.backend.product.rating.model.ProductRating;
import com.techdepot.backend.product.rating.repository.ProductRatingRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductRatingService {

    private final ProductRatingRepository productRatingRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ProductRatingService(ProductRatingRepository productRatingRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository) {
        this.productRatingRepository = productRatingRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public ProductRatingDTO rateProduct(Long productId, ProductRatingRequest request) {
        if (request.getCustomerId() == null) {
            throw new RuntimeException("El cliente es obligatorio.");
        }

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("La calificacion debe estar entre 1 y 5.");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el cliente."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el producto."));

        ProductRating productRating = productRatingRepository
                .findByCustomerIdAndProductId(customer.getId(), product.getId())
                .orElseGet(ProductRating::new);

        LocalDateTime now = LocalDateTime.now();

        if (productRating.getId() == null) {
            productRating.setCustomer(customer);
            productRating.setProduct(product);
            productRating.setCreatedAt(now);
        }

        productRating.setRating(request.getRating());
        productRating.setComment(request.getComment());
        productRating.setUpdatedAt(now);

        return new ProductRatingDTO(productRatingRepository.save(productRating));
    }

    public List<ProductRatingDTO> getProductRatings(Long productId) {
        return productRatingRepository.findByProductIdOrderByUpdatedAtDesc(productId)
                .stream()
                .map(ProductRatingDTO::new)
                .toList();
    }

    public ProductRatingSummary getProductRatingSummary(Long productId) {
        double average = productRatingRepository.getAverageRatingByProductId(productId);
        long total = productRatingRepository.countByProductId(productId);
        double roundedAverage = Math.round(average * 10.0) / 10.0;

        return new ProductRatingSummary(roundedAverage, total);
    }
}
