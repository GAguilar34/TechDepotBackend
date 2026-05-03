package com.techdepot.backend.order.model;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.payment.model.Payment;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity //Convierte la clase en una tabla
@Table(name = "orders") //Nombre de la tabla en MySQL
public class Order {
    @Id //Le dice a Spring boot que es un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a la base de datos que genere un id y lo incremente
    private Long id;
    @ManyToOne //Muchas ordenes pertenecen a un solo cliente
    @JoinColumn(name = "customer_id") //Le dice a la base de datos que cree una nueva columna en la tabla orders
    private Customer customer;
    private double total;
    @Enumerated(EnumType.STRING) //Le dice jpa como guardar este campo 
    private Status status;
    private LocalDateTime createdAt;
    private String address;
    private String phone;
    private String receiverName;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //mappedBy le decimos a jpa que la relacion ya fue definida
    //cascade = CascadeType.ALL le dice a jpa que si hacemos algo con esta entidad tambien lo haga en orderDetails 
    private List<OrderDetail> orderDetail;
    
    public Order(){
        
    }

    public Order(Long id, Customer customer, double total, Status status, LocalDateTime createdAt, String address, String phone, 
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
    public List<OrderDetail> getOrderDeatil(){return orderDetail;}
    
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
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
