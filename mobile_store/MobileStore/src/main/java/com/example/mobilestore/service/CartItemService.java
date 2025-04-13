package com.example.mobilestore.service;

import com.example.mobilestore.dto.CartItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartItemService {
    CartItemDTO getOneProductInCart(String productId);
    Page<CartItemDTO> getAllProductInCart(Pageable pageable);
    CartItemDTO addProductToCart(String username, CartItemDTO cartItemDTO);
    CartItemDTO updateProductInCart(String cartId, CartItemDTO cartItemDTO);
    void deleteProductInCart(String productId);
}
