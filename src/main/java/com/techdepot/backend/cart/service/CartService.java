package com.techdepot.backend.cart.service;

import com.techdepot.backend.cart.model.Cart;
import com.techdepot.backend.cart.model.CartItem;
import com.techdepot.backend.cart.repository.CartRepository;
import com.techdepot.backend.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    CartRepository cartRepository;
    
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    
    public void addProduct(Long cartId, Product product, int amount){
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isEmpty()){
            throw new RuntimeException("No se encontro el carrito");        
        }
        else{
            Cart c = cart.get();
            if (c.getItem() == null) {
                c.setItem(new ArrayList<>());
            }
            for(CartItem items: c.getItem()){
                if(items.getProduct().getId().equals(product.getId())){
                    if(amount > 0){
                    items.setAmount(items.getAmount() + amount);
                    cartRepository.save(c);
                    return;
                    }
                    else{
                        throw new RuntimeException("Por favor agregue una cantidad de productos valida.");
                    }
                }
            }
           CartItem newItem = new CartItem(product, amount, product.getPrice());
           c.getItem().add(newItem);
           
           cartRepository.save(c);
        }
    }
    
    public void deleteProduct(Long cartId, Product product){
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isEmpty()){
            throw new RuntimeException("No se encontro el carrito");        
        }
        else{
            Cart c = cart.get();
            if (c.getItem() != null) {
                c.getItem().removeIf(item -> item.getProduct().getId().equals(product.getId()));
                cartRepository.save(c);
            }
        }
    }
    
    public List<CartItem> getItems(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()){
          throw new RuntimeException("No se encontro el carrito");  
        }
        else{
        Cart c = cart.get();
        return c.getItem() != null ? c.getItem() : new ArrayList<>();
        }
    }
    
    public void clearCart(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()){
          throw new RuntimeException("No se encontro el carrito");  
        }
        else{
            Cart c = cart.get();
            if (c.getItem() != null) {
                c.getItem().clear();
                cartRepository.save(c);
            }
        }
    }
    
    public Cart getCartByCustomer(Long customerId) {

    return cartRepository
            .findByCustomerId(customerId)
            .orElseThrow(() ->
                    new RuntimeException("No se encontró carrito"));
}
}
