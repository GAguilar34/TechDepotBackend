package com.techdepot.backend.cart.dto;

import com.techdepot.backend.cart.model.Cart;
import com.techdepot.backend.cart.model.CartItem;
import com.techdepot.backend.customer.model.Customer;
import java.util.List;

public class CartDTO {
    
    private Long id;
    private Customer customer;
    private List<CartItem> cartItem;
    private double total;
    
    public CartDTO(Cart cart){
        this.id = cart.getId();
        this.customer = cart.getCustomer();
        this.cartItem = cart.getItem();
        this.total = cart.getTotal();
    }
    
    public CartDTO(Long id, Customer customer, List<CartItem> cartItem, double total){
        this.id = id;
        this.customer = customer;
        this.cartItem = cartItem;
        this.total = total;
    }
    
    //Getters
    public Long getId(){return id;}
    public Customer getCustomer(){return customer;}
    public List<CartItem> getItem(){return cartItem;}
    public double getTotal(){return total;}
    
    //Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
