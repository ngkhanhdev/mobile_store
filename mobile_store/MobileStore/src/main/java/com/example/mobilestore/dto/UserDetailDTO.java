package com.example.mobilestore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    @Valid
    private String id;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String middlename;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String address;

    @NotEmpty(message = "Phone is mandatory")
    @Pattern(regexp = "^(091|094|088|083|084|085|081|082|086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|076|078|087|055)\\d{7}$",
            message = "Phone must be a valid number with correct prefix")
    @NotEmpty
    private String phone;
    @NotEmpty
    private String userId;
    private String imageUrl;
    private String imageName;
    private Date createAt;
}
