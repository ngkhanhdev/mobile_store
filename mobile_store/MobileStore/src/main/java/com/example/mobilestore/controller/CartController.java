package com.example.mobilestore.controller;

import com.example.mobilestore.dto.CartDTO;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.service.CartService;
import com.example.mobilestore.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    @GetMapping("/{username}")
    public ResponseEntity<BaseResponse<CartDTO>> getCartByUsername(@PathVariable String username){
        return ResponseFactory.ok(cartService.getCartByUsername(username));
    }
}
