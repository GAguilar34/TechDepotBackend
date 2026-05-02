package com.techdepot.backend.cart.controller;

import com.techdepot.backend.cart.model.CartItem;
import com.techdepot.backend.cart.service.CartService;
import com.techdepot.backend.product.model.Product;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Permite que esta clase maneje peticiones HTTP y devuelva datos en formato JSON
@RequestMapping("/cart")//Sirve para que el backend tenga una ruta para que acceda el Frontend 
@CrossOrigin //Sirve para que el navegador permita que otros origenes usen este backend
public class CartController {
    CartService service;
    
    public CartController(CartService service){
        this.service = service;
    }
    
    @PostMapping("/{cartId}/add") //Define el metodo que responde a la peticion HTTP
    public void addProduct(@PathVariable Long id, @RequestBody Product product, @PathVariable int amount){
        service.addProduct(id, product, amount);
    }
    
    @DeleteMapping("/{cartId}/product/{productId}")//Eliminamos el objeto mediante el id recibido
    public void deleteProduct(@PathVariable Long cartId, @RequestBody Product product){
        service.deleteProduct(cartId, product);
    }
    
    //Obtiene toda la lista 
    @GetMapping("/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId){
        return service.getItems(cartId);
    } 
    
    //Vaciar carrito
    @DeleteMapping("/{cartId}/clear")
    public void clearCart(@PathVariable Long cartId){
        service.clearCart(cartId);
    }
}
