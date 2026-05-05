package com.techdepot.backend.payment.repository;

import com.techdepot.backend.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
