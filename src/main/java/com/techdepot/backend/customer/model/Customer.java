package com.techdepot.backend.customer.model;

import jakarta.persistence.*;

@Entity //Convierte la clase en una tabla
@Table(name = "customer") //Nombre de la tabla en MySQL
public class Customer {

    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    private int age;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String accountNumber;

    public Customer() {
    }

    public Customer(Long id, int age, String name, String email, String gender, 
            String phone, String password, UserType userType, String accountNumber) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
        this.accountNumber = accountNumber;
    }

    //Getters
    public Long getId() {return id;}
    public int getAge() {return age;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getGender() {return gender;}
    public String getPhone() {return phone;}
    public String getPassword() {return password;}
    public UserType getUserType() { return userType; }
    public String getAccountNumber() { return accountNumber; }

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

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserType(UserType userType) { 
        this.userType = userType;
    } 
    
    public void setAccountNumber(String accountNumber) { 
        this.accountNumber = accountNumber; 
    }
}
