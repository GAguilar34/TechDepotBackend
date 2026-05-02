package com.techdepot.backend.cart.repository;

import com.techdepot.backend.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
}
