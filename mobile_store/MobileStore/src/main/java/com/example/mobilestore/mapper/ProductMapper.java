package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.BrandDTO;
import com.example.mobilestore.dto.ProductDTO;
import com.example.mobilestore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = BrandDTO.class)
@Component
public interface ProductMapper {
    @Mapping(source = "brand", target = "brand")
    ProductDTO toProductDTO(Product product);
    @Mapping(source = "brand",target = "brand")
    Product toProduct(ProductDTO productDTO);
}
