package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.ProductDTO;
import com.example.mobilestore.entity.Brand;
import com.example.mobilestore.entity.Product;
import com.example.mobilestore.exception.AlreadyExistsException;
import com.example.mobilestore.exception.NotFoundException;
import com.example.mobilestore.mapper.ProductMapper;
import com.example.mobilestore.repository.BrandRepository;
import com.example.mobilestore.repository.ProductRepository;
import com.example.mobilestore.service.CloudinaryService;
import com.example.mobilestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public Page<ProductDTO> getAllBrand(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toProductDTO);
    }

    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile imageProduct) throws IOException {
//        System.out.println("Product: " +productDTO.getBrand().getId());
//        System.out.println("image: "+imageProduct.getOriginalFilename());
        if(productRepository.existsByName(productDTO.getName())){
            throw new AlreadyExistsException("Product", "name", productDTO.getName());
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        //brand
        Brand brand = brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(()-> new NotFoundException(productDTO.getBrandId()));
        product.setBrand(brand);

        //imageProduct
        Map<String, Object> image = cloudinaryService.uploadImage(imageProduct);
        product.setImageUrl(image.get("secure_url").toString());
        String publicId = image.get("public_id").toString();
        String[] parts = publicId.split("/");
        String imageName = parts[parts.length - 1];
        product.setImageName(imageName);

        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO, MultipartFile imageProduct) throws IOException {
//        System.out.println("brand: " +productDTO.getBrandId());
//        System.out.println("image: " +imageProduct.getOriginalFilename());
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        //brand
        Brand brand = brandRepository.findById(productDTO.getBrandId())
                        .orElseThrow(()-> new NotFoundException(productDTO.getBrandId()));
        product.setBrand(brand);
        //ImageProduct
        System.out.println("imageName: "+product.getImageName());

        Map<String, Object> image = cloudinaryService.updateImage(imageProduct, product.getImageName());
        String publicId = image.get("public_id").toString();
        String[] parts = publicId.split("/");
        String imageName = parts[parts.length - 1];
        product.setImageName(imageName);
        product.setImageUrl(image.get("secure_url").toString());

        productRepository.save(product);

        return productMapper.toProductDTO(product);
    }

}
