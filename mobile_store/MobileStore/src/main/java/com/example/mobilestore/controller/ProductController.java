package com.example.mobilestore.controller;

import com.example.mobilestore.dto.CustomPage;
import com.example.mobilestore.dto.ProductDTO;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.service.ProductService;
import com.example.mobilestore.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductDTO>> getProductById(@PathVariable String id) throws IOException{
        return ResponseFactory.ok(productService.getProductById(id));
    }
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<CustomPage<ProductDTO>>> getAllProduct(@RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "1") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> productDTOPage = productService.getAllBrand(pageable);
        CustomPage<ProductDTO> customPage = new CustomPage<>(productDTOPage);
        return ResponseFactory.ok(customPage);
    }
    @PostMapping()
    public ResponseEntity<BaseResponse<ProductDTO>> createProduct(@Valid @ModelAttribute(name = "product") ProductDTO productDTO,
                                                                  @RequestParam("imageProduct")MultipartFile imageProduct) throws IOException{
        System.out.println("brand: " +productDTO.getBrandId());
        System.out.println("image: " +imageProduct.getOriginalFilename());
        ProductDTO product = productService.createProduct(productDTO, imageProduct);
        return ResponseFactory.ok(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductDTO>> updateProduct(@PathVariable String id,
                                                                  @ModelAttribute(name = "productDTO") ProductDTO productDTO,
                                                                  @RequestParam("imageProduct") MultipartFile imageProduct) throws IOException{


        return ResponseFactory.ok(productService.updateProduct(id, productDTO, imageProduct));
    }

}
