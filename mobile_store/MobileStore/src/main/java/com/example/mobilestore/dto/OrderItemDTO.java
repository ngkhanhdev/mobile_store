package com.example.mobilestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private String id;
    private String orderId;
    private ProductDTO product;
    private Integer quantity;
    private Double price;

}
