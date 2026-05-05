package com.techdepot.backend.favorites.repository;

import com.techdepot.backend.favorites.model.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    
    List<Favorite> findByCustomerId(Long customerId);
    boolean existsByCustomerIdAndProductId(Long customerId, Long productId);
}
