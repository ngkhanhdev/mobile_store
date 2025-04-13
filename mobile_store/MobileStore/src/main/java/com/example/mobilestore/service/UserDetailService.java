package com.example.mobilestore.service;

import com.example.mobilestore.dto.UserDetailDTO;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface UserDetailService {
    UserDetailDTO getUserDetail(String username);
    UserDetailDTO createProfile(UserDetailDTO userDetailDTO, MultipartFile avatar) throws IOException;
    UserDetailDTO updateUserDetail(String id, UserDetailDTO userDetailDTO, MultipartFile avatar) throws IOException;

}
