package com.techdepot.backend.order.dto;

import java.util.List;

public class OrderRequest {

    private Long customerId;
    private String address;
    private String phone;
    private String receiverName;
    private Long paymentId;

    private List<OrderItemRequest> items;

    public OrderRequest(){}

    // Getters
    public Long getCustomerId() { return customerId; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getReceiverName() { return receiverName; }
    public Long getPaymentId() { return paymentId; }
    public List<OrderItemRequest> getItems() { return items; }

    // Setters
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}