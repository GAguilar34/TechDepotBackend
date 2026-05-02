package com.techdepot.backend.product.controller;

import com.techdepot.backend.product.model.Product;
import com.techdepot.backend.product.service.ProductService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController //Permite que esta clase maneje peticiones HTTP y devuelva datos en formato JSON
@RequestMapping("/products")//Sirve para que el backend tenga una ruta para que acceda el Frontend 
@CrossOrigin //Sirve para que el navegador permita que otros origenes usen este backend
public class ProductController {
    
    ProductService service;
    
    public ProductController(ProductService service){
        this.service = service;
    }
    
    @PostMapping //Define el metodo que responde a la peticion HTTP
    public void create(@RequestBody Product product){
        service.createProduct(product);
    }
    
    @PatchMapping("/{id}") //Define que este metodo define a una peticion patch es decir actualizar
    public void update(@PathVariable Long id, @RequestBody Product product){//PathVariable sirve para que el backend acceda al id que viene del Frontend
        service.updateProduct(id, product);
    }
    
    @DeleteMapping("/{id}")//Eliminamos el objeto mediante el id recibido
    public void delete(@PathVariable Long id){
        service.deleteProduct(id);
    }
    
    @GetMapping//Obtiene toda la lista 
    public List<Product> getAll(){
        return service.getAllProduct();
    }
}
