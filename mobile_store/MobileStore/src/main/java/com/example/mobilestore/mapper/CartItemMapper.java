package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.CartItemDTO;
import com.example.mobilestore.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
@Component
public interface CartItemMapper {
    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "product", target = "product")
    CartItemDTO toCartItemDTO(CartItem cartItem);

    @Mapping(source = "cartId", target = "cart.id")
    @Mapping(source = "product", target = "product")
    CartItem toCartItem(CartItemDTO cartItemDTO);
}
