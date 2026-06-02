package com.techdepot.backend.product.rating.model;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.product.model.Product;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "product_rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "product_id"})
)
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductRating() {
    }

    public Long getId() {return id;}
    public Customer getCustomer() {return customer;}
    public Product getProduct() {return product;}
    public int getRating() {return rating;}
    public String getComment() {return comment;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
