package com.techdepot.backend.favorites.model;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.product.model.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Favorite() {}

    // Getters
    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Product getProduct() { return product; }

    // Setters
    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }
    
    public void setProduct(Product product) { 
        this.product = product; 
    }
}