package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.CartDTO;
import com.example.mobilestore.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartMapper {
    @Mapping(source = "user.id", target = "userId")
    CartDTO toCartDTO(Cart cart);
    Cart toCart(CartDTO cartDTO);
}
