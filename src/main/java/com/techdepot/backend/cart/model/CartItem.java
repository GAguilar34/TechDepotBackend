package com.techdepot.backend.cart.model;

import com.techdepot.backend.product.model.Product;
import jakarta.persistence.*;

@Entity //Convierte la clase en una tabla
public class CartItem {
    
    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    @ManyToOne//Muchos cartItem pertenecen a un producto
    @JoinColumn(name = "product_id") //Crea una columna en la tabla cartItem llamada product_id
    private Product product;
    private int amount;
    private double price;
    private double subTotal;
    
    public CartItem(Long id, Product product, int amount, double price, double subTotal){
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.subTotal = subTotal;
    }
    
    public CartItem(Product product, int amount, double price){
        this.product = product;
        this.amount = amount;
        this.price = price;
    }
    
    public CartItem(){
        
    }
    
    //Getters 
    public Long getId(){return id;        }
    public Product getProduct() {return product;}
    public int getAmount() {return amount;}
    public double getPrice() {return price * amount;}
    public double getSubTotal() {return price * amount;}
    
    //Setters
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
}
