package com.techdepot.backend.customer.repository;

import com.techdepot.backend.customer.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findAll(List<Customer> customer);
}
