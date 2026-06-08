package com.techdepot.backend.product.repository;

import com.techdepot.backend.product.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findBySellerId(Long sellerId);
    
}
