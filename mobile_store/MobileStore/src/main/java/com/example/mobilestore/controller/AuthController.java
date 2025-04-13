package com.example.mobilestore.controller;

import com.example.mobilestore.dto.JwtDTO;
import com.example.mobilestore.dto.UserDTO;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.service.UserService;
import com.example.mobilestore.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<UserDTO>> sigup(@Valid @RequestBody UserDTO userDTO){
        return ResponseFactory.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<JwtDTO>> login(@RequestBody UserDTO loginDTO) {
        return ResponseFactory.ok(userService.login(loginDTO));
    }
//    @GetMapping("/list")
//    public ResponseEntity<BaseResponse<CustomPage<UserDTO>>> getAllUser(@RequestParam(defaultValue = "0") Integer page,
//                                                                        @RequestParam(defaultValue = "2") Integer size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<UserDTO> userPage = userService.getAllUser(pageable);
//        CustomPage<UserDTO> customPage = new CustomPage<>(userPage);
//        return ResponseFactory.ok(customPage);
//    }
}
