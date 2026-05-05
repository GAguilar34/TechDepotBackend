package com.techdepot.backend.payment.dto;

public class PaymentRequest {
    private double amount;
    private String nameOwner;
    private String lastFourDigits;
    private String expirationDate;

    public PaymentRequest() {
    }

    // Getters
    public double getAmount() { return amount; }
    public String getNameOwner() { return nameOwner; }
    public String getLastFourDigits() { return lastFourDigits; }
    public String getExpirationDate() { return expirationDate; }

    // Setters
    public void setAmount(double amount) { this.amount = amount; }
    public void setNameOwner(String nameOwner) { this.nameOwner = nameOwner; }
    public void setLastFourDigits(String lastFourDigits) { this.lastFourDigits = lastFourDigits; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
}
