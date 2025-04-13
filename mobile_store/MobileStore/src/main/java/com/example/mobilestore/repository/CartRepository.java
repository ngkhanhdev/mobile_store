package com.example.mobilestore.repository;

import com.example.mobilestore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
