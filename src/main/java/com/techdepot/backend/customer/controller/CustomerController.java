package com.techdepot.backend.customer.controller;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.service.CustomerService;
import java.util.List;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController //Permite que esta clase maneje peticiones HTTP y devuelva datos en formato JSON
@RequestMapping("/customers")//Sirve para que el backend tenga una ruta para que acceda el Frontend 
@CrossOrigin //Sirve para que el navegador permita que otros origenes usen este backend
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping //Define el metodo que responde a la peticion HTTP
    public ResponseEntity<?> create(@RequestBody Customer customer) { //RequestBody convierte los datos de formato JSON a objeto
        try {
            Customer createdCustomer = service.createCustomer(customer);
            return ResponseEntity.ok(createdCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}") //Define que este metodo define a una peticion patch es decir actualizar
    public void update(@PathVariable Long id, @RequestBody Customer customer) { //PathVariable sirve para que el backend acceda al id que viene del Frontend
        service.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")//Eliminamos el objeto mediante el id recibido
    public void delete(@PathVariable Long id) {
        service.deleteCustomer(id);
    }

    @GetMapping//Obtiene toda la lista 
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Customer customer = service.getCustomerById(id);
            return ResponseEntity.ok(
                    customer
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login") //Define el metodo que responde a la peticion HTTP
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        try {
            Customer c = service.login(customer.getEmail(), customer.getPassword());
            return ResponseEntity.ok(c);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
