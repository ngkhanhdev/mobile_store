package com.example.mobilestore.dto;

import com.example.mobilestore.entity.Brand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
//    @JsonProperty("product")
    private String id;
    private String name;
    private String description;
    private double price;
    private String imageName;
    private String imageUrl;
//    @JsonProperty("brandId")
    @JsonIgnore
    private String brandId;
    private Brand brand;
    private Date createAt;
}
