package com.techdepot.backend.order.repository;

import com.techdepot.backend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
