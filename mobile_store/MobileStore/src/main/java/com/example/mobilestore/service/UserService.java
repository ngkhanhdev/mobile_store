package com.example.mobilestore.service;

import com.example.mobilestore.dto.JwtDTO;
import com.example.mobilestore.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO register(UserDTO user);
    JwtDTO login(UserDTO user);
    Page<UserDTO> getAllUser(Pageable pageable);
}
