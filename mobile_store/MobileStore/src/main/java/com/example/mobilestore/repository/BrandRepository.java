package com.example.mobilestore.repository;

import com.example.mobilestore.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByName(String name);
    boolean existsByName(String name);
}
