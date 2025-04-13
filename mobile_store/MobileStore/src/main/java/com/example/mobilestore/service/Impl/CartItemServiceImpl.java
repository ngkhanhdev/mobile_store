package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.CartDTO;
import com.example.mobilestore.dto.CartItemDTO;
import com.example.mobilestore.dto.ProductDTO;
import com.example.mobilestore.entity.Cart;
import com.example.mobilestore.entity.CartItem;
import com.example.mobilestore.entity.Product;
import com.example.mobilestore.exception.NotFoundException;
import com.example.mobilestore.mapper.CartItemMapper;
import com.example.mobilestore.mapper.CartMapper;
import com.example.mobilestore.repository.CartItemRepository;
import com.example.mobilestore.repository.ProductRepository;
import com.example.mobilestore.service.CartItemService;
import com.example.mobilestore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    @Override
    public CartItemDTO getOneProductInCart(String productId) {
        return null;
    }

    @Override
    public Page<CartItemDTO> getAllProductInCart(Pageable pageable) {
        return null;
    }

    @Override
    public CartItemDTO addProductToCart(String username, CartItemDTO cartItemDTO) {


        //get cart with username
        CartDTO cart = cartService.getCartByUsername(username);
        //find product in cartitem
        CartItem existCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), cartItemDTO.getProduct().getId())
                .orElseThrow(()-> new NotFoundException(cart.getId()));
        if(existCartItem!=null){


        } else {
            CartItem newCartItem = new CartItem();

            newCartItem.setCart(cartMapper.toCart(cart));
            newCartItem.setQuantity(cartItemDTO.getQuantity());
            //product
            Product product = productRepository.findById(cartItemDTO.getProduct().getId())
                    .orElseThrow(()-> new NotFoundException(cartItemDTO.getProduct().getId()));
            newCartItem.setProduct(product);
            cartItemRepository.save(newCartItem);
            return cartItemMapper.toCartItemDTO(newCartItem);
        }
    }

    @Override
    public CartItemDTO updateProductInCart(String cartId, CartItemDTO cartItemDTO) {
        return null;
    }

    @Override
    public void deleteProductInCart(String productId) {

    }
}
