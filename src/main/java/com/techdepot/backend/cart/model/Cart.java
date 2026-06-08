package com.techdepot.backend.cart.model;

import com.techdepot.backend.customer.model.Customer;

import jakarta.persistence.*;
import java.util.List;

@Entity //Convierte la clase en una tabla
@Table(name = "cart") //Nombre de la tabla en MySQL

public class Cart {
    
    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    @OneToOne //Relacion uno a uno 
    @JoinColumn(name = "customer_id") //Crea una columna en la tabla Cart llamada customer_id
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //Relacion uno a muchos 
    @JoinColumn(name = "cart_id") //Crea una columna en la tabla CartItem llamada cart_id
    private List<CartItem> cartItem;
    private double total;
    
    public Cart(Long id, Customer customer, List<CartItem> cartItem, double total){
        this.id = id;
        this.customer = customer;
        this.cartItem = cartItem;
        this.total = total;
    }
    
    public Cart(){
        
    }
    
    //Getters
    public Long getId(){return id;}
    public Customer getCustomer(){return customer;}
    public List<CartItem> getItem(){return cartItem;}
    public double getTotal(){
        total = 0;
        
        if (cartItem != null) {
            for(CartItem item: cartItem){
                total += item.getSubTotal();
            }
        }
        return total;
    }
    
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
