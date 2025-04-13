package com.example.mobilestore.service;

import com.example.mobilestore.dto.CartDTO;

public interface CartService {
    CartDTO getCartByUsername(String username);
}
