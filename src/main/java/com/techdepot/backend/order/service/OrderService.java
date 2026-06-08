package com.techdepot.backend.order.service;

import com.techdepot.backend.cart.model.Cart;
import com.techdepot.backend.cart.model.CartItem;
import com.techdepot.backend.cart.repository.CartRepository;
import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.repository.CustomerRepository;
import com.techdepot.backend.order.dto.OrderItemRequest;
import com.techdepot.backend.order.dto.OrderRequest;
import com.techdepot.backend.order.model.Order;
import com.techdepot.backend.order.model.OrderDetail;
import com.techdepot.backend.order.model.Status;
import com.techdepot.backend.order.repository.OrderRepository;
import com.techdepot.backend.product.model.Product;
import com.techdepot.backend.product.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service //Le dice a Spring boot que es parte de la logica de negocio

public class OrderService {

    OrderRepository orderRepository;
    ProductRepository productRepository;
    CustomerRepository customerRepository;
    CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, 
            CustomerRepository customerRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public void createOrder(OrderRequest orderRequest) {
        List<OrderDetail> details = new ArrayList<>();
        Order order = new Order();
        double total = 0;

        Optional<Customer> customers = customerRepository.findById(orderRequest.getCustomerId());
        if (customers.isEmpty()) {
            throw new RuntimeException("No se encontro el cliente.");
        }
        Customer c = customers.get();

        order.setTotal(total);
        order.setCreatedAt(LocalDateTime.now());
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        order.setReceiverName(orderRequest.getReceiverName());
        order.setCustomer(c);

        if (order.getAddress() == null || order.getPhone() == null || order.getReceiverName() == null) {
            throw new RuntimeException("Por favor llene todos los campos.");
        }

        for (OrderItemRequest item : orderRequest.getItems()) {
            if (item.getAmount() < 0) {
                throw new RuntimeException("Por favor seleccione una cantidad valida de productos");
            } 
            Long productId = item.getProductId();
            Optional<Product> products = productRepository.findById(productId);
            if (products.isEmpty()) {
                throw new RuntimeException("No se encontro el producto.");
            } 
                OrderDetail detail = new OrderDetail();
                Product p = products.get();
                detail.setProduct(p);
                detail.setAmount(item.getAmount());
                detail.setPrice(p.getPrice());
                detail.setOrder(order);
                details.add(detail);
               
                total += item.getAmount() * p.getPrice();
            
        }
        order.setStatus(Status.PENDIENTE);
        order.setTotal(total);
        order.setOrderDetail(details);
        orderRepository.save(order);
    }

    public void createOrderFromCart(Long cartId, OrderRequest orderRequest) {
        List<OrderDetail> details = new ArrayList<>();
        double total = 0;

        Optional<Cart> carts = cartRepository.findById(cartId);
        if (carts.isEmpty()) {
            throw new RuntimeException("No se encontro el carrito.");
        }
        Cart c = carts.get();
        if (c.getItem() == null || c.getItem().isEmpty()) {
            throw new RuntimeException("El carrito no tiene productos.");
        }
        Optional<Customer> customers = customerRepository.findById(orderRequest.getCustomerId());
        if (customers.isEmpty()) {
            throw new RuntimeException("No se encontro el cliente.");
        }
        Customer customer = customers.get();

        Order order = new Order();
        order.setCustomer(customer);
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        order.setReceiverName(orderRequest.getReceiverName());
        order.setCreatedAt(LocalDateTime.now());

        if (order.getAddress() == null || order.getPhone() == null || order.getReceiverName() == null) {
            throw new RuntimeException("Por favor llene todos los campos.");
        }

        for (CartItem item : c.getItem()) {
            if (item.getProduct() == null) {
                throw new RuntimeException("Hay un producto invalido en el carrito.");
            }
            OrderDetail detail = new OrderDetail();
            detail.setProduct(item.getProduct());
            detail.setAmount(item.getAmount());
            detail.setPrice(item.getProduct().getPrice());
            detail.setOrder(order);
            details.add(detail);

            total += item.getAmount() * item.getProduct().getPrice();
        }

        order.setStatus(Status.PENDIENTE);
        order.setTotal(total);
        order.setOrderDetail(details);
        orderRepository.save(order);

        c.getItem().clear();
        cartRepository.save(c);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getSalesBySeller(Long sellerId) {
        return orderRepository.findOrdersBySellerId(sellerId);
    }
}
