package com.techdepot.backend.product.repository;

import com.techdepot.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
