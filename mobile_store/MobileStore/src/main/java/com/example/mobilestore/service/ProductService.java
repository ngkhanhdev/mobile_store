package com.example.mobilestore.service;

import com.example.mobilestore.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    Page<ProductDTO> getAllBrand(Pageable pageable);
    ProductDTO getProductById(String id);
    ProductDTO createProduct(ProductDTO productDTO, MultipartFile imageProduct) throws IOException;
    ProductDTO updateProduct(String id, ProductDTO productDTO, MultipartFile imageProduct) throws IOException;
}
