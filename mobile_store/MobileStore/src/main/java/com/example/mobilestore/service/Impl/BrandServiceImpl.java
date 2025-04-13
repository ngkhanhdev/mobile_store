package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.BrandDTO;
import com.example.mobilestore.entity.Brand;
import com.example.mobilestore.exception.AlreadyExistsException;
import com.example.mobilestore.exception.NotFoundException;
import com.example.mobilestore.mapper.BrandMapper;
import com.example.mobilestore.repository.BrandRepository;
import com.example.mobilestore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    @Override
    public Page<BrandDTO> getAllBrand(Pageable pageable) {
        return brandRepository.findAll(pageable)
                .map(brandMapper::toBrandDTO);
    }

    @Override
    public BrandDTO getBrand(String id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
        return brandMapper.toBrandDTO(brand);
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
//        System.out.println("Brand: " +brandDTO.getName());
        if(brandRepository.existsByName(brandDTO.getName())){
            throw new AlreadyExistsException("Brand", "brandName", brandDTO.getName());
        }
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brandRepository.save(brand);
        return brandMapper.toBrandDTO(brand);
    }

    @Override
    public BrandDTO updateBrand(String id, BrandDTO brandDTO) {
        if(!brandRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        brand.setId(id);
        brand.setName(brandDTO.getName());
//        System.out.println("brand " + brand.getName());
        return brandMapper.toBrandDTO(brandRepository.save(brand));

    }

    @Override
    public void deleteBrand(String id) {
        if(!brandRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        brandRepository.deleteById(id);

    }
}
