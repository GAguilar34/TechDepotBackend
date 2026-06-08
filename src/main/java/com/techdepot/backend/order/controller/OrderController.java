package com.techdepot.backend.order.controller;

import com.techdepot.backend.order.dto.OrderRequest;
import com.techdepot.backend.order.model.Order;
import com.techdepot.backend.order.service.OrderService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Permite que esta clase maneje peticiones HTTP y devuelva datos en formato JSON
@RequestMapping("/order")//Sirve para que el backend tenga una ruta para que acceda el Frontend 
@CrossOrigin //Sirve para que el navegador permita que otros origenes usen este backend
public class OrderController {
    OrderService service;
    
    public OrderController(OrderService service){
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest){
        try{
            service.createOrder(orderRequest);
            return ResponseEntity.ok("Orden Creada exitosamente.");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage()); 
        }
    }
    
    @PostMapping("/{cartId}/add") //Define el metodo que responde a la peticion HTTP
    public ResponseEntity<String> createOrderFromCart(@PathVariable Long cartId, @RequestBody OrderRequest orderRequest){
        try{
            service.createOrderFromCart(cartId, orderRequest);
            return ResponseEntity.ok("Orden Creada desde el carrito exitosamente.");
        }catch(Exception e){
           return ResponseEntity.badRequest().body(e.getMessage()); 
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return service.getOrdersByCustomer(customerId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Order> getSalesBySeller(@PathVariable Long sellerId) {
        return service.getSalesBySeller(sellerId);
    }
}