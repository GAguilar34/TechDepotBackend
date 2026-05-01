package com.techdepot.backend.customer.dto;

import com.techdepot.backend.customer.model.Customer;

public class CustomerDTO {
    
    private Long id;
    private int age;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String password;
    
    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.age = customer.getAge();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.gender = customer.getGender();
        this.phone = customer.getPhone();
        this.password = customer.getPassword();
    }  
    
    public CustomerDTO(Long id, int age, String email, String gender, String phone, String password){
        this.id = id;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }
    
    //Getters
    public Long getId() {return id;}
    public int getAge() {return age;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getGender() {return gender;}
    public String getPhone() {return phone;}
    public String getPassword(){return password;}
    
    //Setters
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
