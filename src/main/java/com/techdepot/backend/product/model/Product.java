package com.techdepot.backend.product.model;

import com.techdepot.backend.customer.model.Customer;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String state;
    private String category;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Customer seller;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", length = 1000)
    private List<String> imageUrls = new ArrayList<>();

    public Product(long id, String nameProduct, String description, int amount, double price, String category, String state, Customer seller, List<String> imageUrls) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.state = state;
        this.category = category;
        this.seller = seller;
        this.imageUrls = imageUrls;
    }
    
    public Product(){
        
    }
    
    //Getters
    public Long getId() {return id;}
    public String getNameProduct() {return nameProduct;}
    public String getDescription() {return description;}
    public int getAmount() {return amount;}
    public double getPrice() {return price;}
    public String getState() {return state;}
    public String getCategory() {return category;}
    public Customer getSeller() {return seller;}
    public List<String> getImageUrls() {return imageUrls;}
    public String getImageUrl() {
        return imageUrls == null || imageUrls.isEmpty() ? null : imageUrls.get(0);
    }
    
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
    
    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setSeller(Customer seller) {
        this.seller = seller;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrls = new ArrayList<>();
        if (imageUrl != null && !imageUrl.isBlank()) {
            this.imageUrls.add(imageUrl);
        }
    }
}    
