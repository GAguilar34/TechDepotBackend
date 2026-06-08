package com.techdepot.backend.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techdepot.backend.order.model.Order;
import com.techdepot.backend.product.model.Product;
import jakarta.persistence.*;

@Entity //Convierte la clase en una tabla
@Table(name = "orderDetail") //Nombre de la tabla en MySQL
public class OrderDetail {

    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    @ManyToOne //Muchos orderDetail pertenecen a un solo producto
    @JoinColumn(name = "product_id")//Le dice a la base de datos que cree una nueva columna en la tabla orderDetail
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    private int amount;
    private double price;

    public OrderDetail() {

    }

    public OrderDetail(Long id, Product product, Order order, int amount, double price) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.amount = amount;
        this.price = price;
    }

    //Getters
    public Long getId() {return id;}
    public Product getProduct() {return product;}
    public Order getOrder() {return order;}
    public int getAmount() {return amount;}
    public double getPrice() {return price;}

    //Setters
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
