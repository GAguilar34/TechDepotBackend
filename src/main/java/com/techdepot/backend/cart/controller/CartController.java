package com.techdepot.backend.cart.controller;

import com.techdepot.backend.cart.model.Cart;
import com.techdepot.backend.cart.model.CartItem;
import com.techdepot.backend.cart.service.CartService;
import com.techdepot.backend.product.model.Product;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/{cartId}/add/{amount}")
    public void addProduct(
            @PathVariable Long cartId,
            @PathVariable int amount,
            @RequestBody Product product) {

        service.addProduct(cartId, product, amount);
    }

    @DeleteMapping("/{cartId}/product")
    public void deleteProduct(
            @PathVariable Long cartId,
            @RequestBody Product product) {

        service.deleteProduct(cartId, product);
    }

    @GetMapping("/customer/{customerId}")
    public Cart getCartByCustomer(
            @PathVariable Long customerId) {

        return service.getCartByCustomer(customerId);
    }

    @DeleteMapping("/{cartId}/clear")
    public void clearCart(@PathVariable Long cartId) {
        service.clearCart(cartId);
    }
}
