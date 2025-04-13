package com.example.mobilestore.controller;

import com.example.mobilestore.dto.BrandDTO;
import com.example.mobilestore.dto.CustomPage;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.repository.BrandRepository;
import com.example.mobilestore.service.BrandService;
import com.example.mobilestore.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<BrandDTO>> getBrandById(@PathVariable String id){
        return ResponseFactory.ok(brandService.getBrand(id));
    }
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<CustomPage<BrandDTO>>> getAllBrand(@RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "1") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<BrandDTO> brandDTOPage = brandService.getAllBrand(pageable);
        CustomPage<BrandDTO> customPage = new CustomPage<>(brandDTOPage);
        return ResponseFactory.ok(customPage);
    }
    @PostMapping()
    public ResponseEntity<BaseResponse<BrandDTO>> createBrand(@Valid @RequestBody BrandDTO brandDTO){
//        System.out.println("controller: " +brandDTO.getName());
        return ResponseFactory.ok(brandService.createBrand(brandDTO));
    }
    @PutMapping("/{id}")
    private ResponseEntity<BaseResponse<BrandDTO>> updateBrand(@PathVariable String id,@Valid @RequestBody BrandDTO brandDTO){
        BrandDTO brand = brandService.updateBrand(id, brandDTO);
        return ResponseFactory.ok(brand);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteBrand(@PathVariable String id){
        brandService.deleteBrand(id);
        return ResponseFactory.deleteSuccess();
    }

}
