package com.example.mobilestore.repository;

import com.example.mobilestore.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
    Optional<UserDetail> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
