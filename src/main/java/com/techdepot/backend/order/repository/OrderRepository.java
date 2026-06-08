package com.techdepot.backend.order.repository;

import com.techdepot.backend.order.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderDetail od JOIN od.product p WHERE p.seller.id = :sellerId")
    List<Order> findOrdersBySellerId(@Param("sellerId") Long sellerId);
    
    List<Order> findByCustomerId(Long customerId);
    
}
