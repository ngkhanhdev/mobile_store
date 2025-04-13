package com.example.mobilestore.service;

import com.example.mobilestore.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    Page<BrandDTO> getAllBrand(Pageable pageable);
    BrandDTO getBrand(String id);
    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(String id, BrandDTO brandDTO);
    void deleteBrand(String id);
}
