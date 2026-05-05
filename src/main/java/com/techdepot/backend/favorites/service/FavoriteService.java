package com.techdepot.backend.favorites.service;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.repository.CustomerRepository;
import com.techdepot.backend.favorites.dto.FavoriteRequest;
import com.techdepot.backend.favorites.model.Favorite;
import com.techdepot.backend.favorites.repository.FavoriteRepository;
import com.techdepot.backend.product.model.Product;
import com.techdepot.backend.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    FavoriteRepository favoriteRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;

    public FavoriteService(FavoriteRepository favoriteRepository,
                           CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void addFavorite(FavoriteRequest request) {

        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
        if (customer.isEmpty()) {
            throw new RuntimeException("No se encontro el cliente.");
        }

        Optional<Product> product = productRepository.findById(request.getProductId());
        if (product.isEmpty()) {
            throw new RuntimeException("No se encontro el producto.");
        }
 
        boolean exists = favoriteRepository.existsByCustomerIdAndProductId(
            request.getCustomerId(), request.getProductId()
        );
        if (exists) {
            throw new RuntimeException("El producto ya esta en favoritos.");
        }

        Favorite favorite = new Favorite();
        favorite.setCustomer(customer.get());
        favorite.setProduct(product.get());
        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long id) {
        Optional<Favorite> favorite = favoriteRepository.findById(id);
        if (favorite.isEmpty()) {
            throw new RuntimeException("No se encontro el favorito.");
        }
        favoriteRepository.delete(favorite.get());
    }

    public List<Favorite> getFavoritesByCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new RuntimeException("No se encontro el cliente.");
        }
        return favoriteRepository.findByCustomerId(customerId);
    }
}
