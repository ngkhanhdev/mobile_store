package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.BrandDTO;
import com.example.mobilestore.entity.Brand;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BrandMapper{
    BrandDTO toBrandDTO(Brand brand);
    Brand toBrand(BrandDTO brandDTO);
}
