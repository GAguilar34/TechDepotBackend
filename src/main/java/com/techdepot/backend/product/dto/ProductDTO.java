package com.techdepot.backend.product.dto;

import com.techdepot.backend.product.model.Product;
import java.util.List;

public class ProductDTO {
    
    private long id;
    private String nameProduct;
    private String description;
    private int amount;
    private double price;
    private String state;
    private String category;
    private List<String> imageUrls;
    private String imageUrl;
    
    public ProductDTO(Product product){
        this.id = product.getId();
        this.nameProduct = product.getNameProduct();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.price = product.getPrice();
        this.state = product.getState();
        this.category = product.getCategory();
        this.imageUrls = product.getImageUrls();
        this.imageUrl = product.getImageUrl();
    }
    
    public ProductDTO(long id, String nameProduct, String description, int amount,double price, String state, String category, List<String> imageUrls){
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.state = state;
        this.category = category;
        this.imageUrls = imageUrls;
        this.imageUrl = imageUrls == null || imageUrls.isEmpty() ? null : imageUrls.get(0);
    }
    
    //Getters
    public long getId() {return id;}
    public String getNameProduct() {return nameProduct;}
    public String getDescription() {return description;}
    public int getAmount() {return amount;}
    public double getPrice() {return price;}
    public String getState() {return state;}
    public String getCategory() {return category;}
    public List<String> getImageUrls() {return imageUrls;}
    public String getImageUrl() {return imageUrl;}
    
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
    
    public void setState(String state){
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        this.imageUrl = imageUrls == null || imageUrls.isEmpty() ? null : imageUrls.get(0);
    }
}
