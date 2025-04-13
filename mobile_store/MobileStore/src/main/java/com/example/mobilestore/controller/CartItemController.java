package com.example.mobilestore.controller;

import com.example.mobilestore.dto.CartItemDTO;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.service.CartItemService;
import com.example.mobilestore.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartitem")
public class CartItemController {
    private final CartItemService cartItemService;
    @PostMapping()
    public ResponseEntity<BaseResponse<CartItemDTO>> addProductToCart(@RequestBody CartItemDTO cartItemDTO){

        CartItemDTO cartItemDTO1 = cartItemService.addProductToCart(cartItemDTO.getUsername(), cartItemDTO);
        return ResponseFactory.ok(cartItemDTO1);
    }
}
