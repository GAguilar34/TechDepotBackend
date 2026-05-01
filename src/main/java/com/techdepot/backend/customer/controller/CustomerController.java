package com.techdepot.backend.customer.controller;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.service.CustomerService;
import java.util.List;

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
    public void create(@RequestBody Customer customer) { //RequestBody convierte los datos de formato JSON a objeto
        service.createCustomer(customer);
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
    public List<Customer> getAll(){
        return service.getAllCustomers();
    }
}
