package com.techdepot.backend.product.rating.repository;

import com.techdepot.backend.product.rating.model.ProductRating;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

    Optional<ProductRating> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<ProductRating> findByProductIdOrderByUpdatedAtDesc(Long productId);

    long countByProductId(Long productId);

    @Query("select coalesce(avg(r.rating), 0) from ProductRating r where r.product.id = :productId")
    double getAverageRatingByProductId(@Param("productId") Long productId);
}
