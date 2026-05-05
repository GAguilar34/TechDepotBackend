package com.techdepot.backend.product.model;

import jakarta.persistence.*;

@Entity //Convierte la clase en una tabla
@Table(name = "product") //Nombre de la tabla en MySQL
public class Product {

    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    private String nameProduct;
    private String description;
    private int amount;
    private double price;
    private String category;
    private String imageUrl; 

    public Product(long id, String nameProduct, String description, int amount, double price, String category, String imageUrl) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }
    
    public Product(){
        
    }
    
    //Getters
    public Long getId() {return id;}
    public String getNameProduct() {return nameProduct;}
    public String getDescription() {return description;}
    public int getAmount() {return amount;}
    public double getPrice() {return price;}
    public String getCategory() {return category;}
    public String getImageUrl() { return imageUrl; }
    
    //Setters
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setImageUrl(String imageUrl) { 
        this.imageUrl = imageUrl; 
    }
}    