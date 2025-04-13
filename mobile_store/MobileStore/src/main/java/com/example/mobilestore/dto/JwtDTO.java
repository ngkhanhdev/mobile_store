package com.example.mobilestore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtDTO {
    private String accessToken;
    private Date expiredIn;
}
