package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.CartDTO;
import com.example.mobilestore.entity.Cart;
import com.example.mobilestore.entity.User;
import com.example.mobilestore.exception.NotFoundException;
import com.example.mobilestore.mapper.CartMapper;
import com.example.mobilestore.repository.CartRepository;
import com.example.mobilestore.repository.UserRepository;
import com.example.mobilestore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    @Override
    public CartDTO getCartByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(username));
//        System.out.println("User: "+user.getUsername());
//        System.out.println("User: "+user.getId());

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalProduct(0);
                    newCart.setTotalAmount(0.0);
                    return cartRepository.save(newCart);
                });
        return cartMapper.toCartDTO(cart);
    }
}
