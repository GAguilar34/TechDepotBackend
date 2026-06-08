package com.techdepot.backend.payment.controller;

import com.techdepot.backend.payment.dto.PaymentRequest;
import com.techdepot.backend.payment.model.Payment;
import com.techdepot.backend.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Permite que esta clase maneje peticiones HTTP y devuelva datos en formato JSON
@RequestMapping("/payment")//Sirve para que el backend tenga una ruta para que acceda el Frontend 
@CrossOrigin //Sirve para que el navegador permita que otros origenes usen este backend
public class PaymentController {
    PaymentService service;
    
    public PaymentController(PaymentService service){
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<?> generatePayment(@RequestBody PaymentRequest paymentRequest){
        try {
            service.generatePayment(
                paymentRequest.getNameOwner(),
                paymentRequest.getExpirationDate(),
                paymentRequest.getLastFourDigits(),
                paymentRequest.getAmount()
            );
            return ResponseEntity.ok("Pago exitoso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
