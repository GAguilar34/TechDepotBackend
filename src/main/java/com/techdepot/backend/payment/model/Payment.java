package com.techdepot.backend.payment.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity //Convierte la clase en una tabla
@Table(name = "payment") //Nombre de la tabla en MySQL
public class Payment {
    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    private String transactionId; 
    private String nameOwner;
    private String expirationDate;
    @Enumerated(EnumType.STRING)
    private Method method;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String lastFourDigits;
    private double amount;
    private LocalDateTime paidAt;
    
    public Payment(){
        
    }

    public Payment(Long id, String transactionId, String nameOwner, String expirationDate, Method method, Status status, String lastFourDigits, double amount, LocalDateTime paidAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.nameOwner = nameOwner;
        this.expirationDate = expirationDate;
        this.method = method;
        this.status = status;
        this.lastFourDigits = lastFourDigits;
        this.amount = amount;
        this.paidAt = paidAt;
    }
    
    //Getters
    public Long getId(){return id;}
    public String getTransactionId(){return transactionId;}
    public String getNameOwner(){return nameOwner;}
    public String getExpirationDate(){return expirationDate;}
    public Method getMethod(){return method;}
    public String getLastFourDigits(){return lastFourDigits;}
    public Status getStatus(){return status;}
    public double getAmount(){return amount;}
    public LocalDateTime getPaidAt(){return paidAt;}
    
    //Setters
    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
    
}
