package com.example.mobilestore.repository;

import com.example.mobilestore.dto.ProductDTO;
import com.example.mobilestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
}
