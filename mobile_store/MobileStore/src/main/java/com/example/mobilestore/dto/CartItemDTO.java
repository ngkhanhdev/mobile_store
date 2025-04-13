package com.example.mobilestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private String id;
    private String cartId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;
    @NotEmpty
    private ProductDTO product;
    @NotEmpty
    private Integer quantity;
}
