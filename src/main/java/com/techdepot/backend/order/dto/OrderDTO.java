package com.techdepot.backend.order.dto;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.order.model.Order;
import com.techdepot.backend.order.model.Status;
import com.techdepot.backend.order.model.OrderDetail;
import com.techdepot.backend.payment.model.Payment;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Customer customer;
    private double total;
    private Status status;
    private LocalDateTime createdAt;
    private String address;
    private String phone;
    private String receiverName;
    private Payment payment;
    private List<OrderDetail> orderDetail;

    public OrderDTO(Long id, Customer customer, double total, Status status, LocalDateTime createdAt, String address, String phone, 
        String receiverName, Payment payment, List<OrderDetail> orderDetail) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
        this.phone = phone;
        this.receiverName = receiverName;
        this.payment = payment;
        this.orderDetail = orderDetail;
    }
    
    public OrderDTO(Order order){
        this.id = order.getId();
        this.customer = order.getCustomer();
        this.total = order.getTotal();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.receiverName = order.getReceiverName();
        this.payment = order.getPayment();
        this.orderDetail = order.getOrderDetail();
    }
    
    //Getters
    public Long getId(){return id;}
    public Customer getCustomer(){return customer;}
    public double getTotal(){return total;}
    public Status getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public String getAddress(){return address;}
    public String getPhone(){return phone;}
    public String getReceiverName(){return receiverName;}
    public Payment getPayment(){return payment;}
    public List<OrderDetail> getOrderDetail(){return orderDetail;}
    
    //Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
