package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.UserDTO;
import com.example.mobilestore.dto.UserDetailDTO;
import com.example.mobilestore.entity.User;
import com.example.mobilestore.entity.UserDetail;
import com.example.mobilestore.exception.AlreadyExistsException;
import com.example.mobilestore.exception.NotFoundException;
import com.example.mobilestore.mapper.UserDetailMapper;
import com.example.mobilestore.repository.UserDetailRepository;
import com.example.mobilestore.repository.UserRepository;
import com.example.mobilestore.service.CloudinaryService;
import com.example.mobilestore.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final UserDetailRepository userDetailRepository;
    private final UserDetailMapper userDetailMapper;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    @Override
    public UserDetailDTO getUserDetail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException(username));
        UserDetail profile = userDetailRepository.findByUserId(user.getId())
                .orElseThrow(()-> new NotFoundException(user.getId()));
        return userDetailMapper.toUserDetailDTO(profile);
    }

    @Override
    public UserDetailDTO createProfile(UserDetailDTO userDetailDTO, MultipartFile avatar) throws IOException {
//        System.out.println("Profile: " +userDetailDTO.getFirstname());
//        System.out.println("Profile: " +userDetailDTO.getMiddlename());
//        System.out.println("Profile: " +userDetailDTO.getLastname());
//        System.out.println("Profile: " +userDetailDTO.getAddress());
//        System.out.println("Profile: " +userDetailDTO.getPhone());
//        System.out.println("Profile: " +userDetailDTO.getUserId());
//        System.out.println("Image: " + avatar.getOriginalFilename());
        if(userDetailRepository.existsByUserId(userDetailDTO.getUserId())){
            throw  new AlreadyExistsException("UserId", "userId", userDetailDTO.getUserId());
        }
        User user = userRepository.findById(userDetailDTO.getUserId())
                .orElseThrow(()-> new NotFoundException(userDetailDTO.getUserId()));
        UserDetail userDetailFind = new UserDetail();
        userDetailFind.setFirstname(userDetailDTO.getFirstname());
        userDetailFind.setMiddlename(userDetailDTO.getMiddlename());
        userDetailFind.setLastname(userDetailDTO.getLastname());
        userDetailFind.setAddress(userDetailDTO.getAddress());
        userDetailFind.setPhone(userDetailDTO.getPhone());
        userDetailFind.setUser(user);
        //avatar
        Map<String, Object> imageProfile = cloudinaryService.uploadImage(avatar);

        userDetailFind.setImageUrl(imageProfile.get("secure_url").toString());
//        userDetailFind.setImageName(imageProfile.get("public_id").toString());
        String publicId = imageProfile.get("public_id").toString();
        String[] parts = publicId.split("/");
        String imageName = parts[parts.length - 1];
        userDetailFind.setImageName(imageName);
//        System.out.println("id: " +userDetailFind.getId());
//        System.out.println("time: " +userDetailFind.getCreateAt());
//        System.out.println("profile: " +userDetailFind.getLastname());
//        System.out.println("image: " +imageProfile.get("secure_url"));
//        System.out.println("Image: " +imageProfile.get("public_id"));
        UserDetail saveUserDetail = userDetailRepository.save(userDetailFind);

        userDetailFind.setId(saveUserDetail.getId());
        userDetailFind.setCreateAt(saveUserDetail.getCreateAt());
        return userDetailMapper.toUserDetailDTO(userDetailFind);
    }

    @Override
    public UserDetailDTO updateUserDetail(String id, UserDetailDTO userDetailDTO, MultipartFile avatar) throws IOException{
//        System.out.println("id: " +id);
//        System.out.println("profile: " +userDetailDTO.getLastname());
//        System.out.println("image: " +avatar.getOriginalFilename());
//        System.out.println("user: " +userDetailDTO.getUserId());
        UserDetail profile = userDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
        User user = userRepository.findById(userDetailDTO.getUserId())
                .orElseThrow(()-> new NotFoundException(userDetailDTO.getUserId()));
//        System.out.println("profile: " +profile.getLastname());
//        System.out.println("userId: " +userDetailDTO.getUserId());
//        System.out.println("user; " +user.getRole().getRoleName());
        profile.setFirstname(userDetailDTO.getFirstname());
        profile.setMiddlename(userDetailDTO.getMiddlename());
        profile.setLastname(userDetailDTO.getLastname());
        profile.setUser(user);
        profile.setAddress(userDetailDTO.getAddress());
        profile.setPhone(userDetailDTO.getPhone());

    //avatar
        Map<String, Object> imageProfile = cloudinaryService.updateImage(avatar, profile.getImageName());
        String publicId = imageProfile.get("public_id").toString();
        String[] parts = publicId.split("/");
        String imageName = parts[parts.length - 1];
        profile.setImageName(imageName);
        profile.setImageUrl(imageProfile.get("secure_url").toString());
        userDetailRepository.save(profile);
//        return null;
        return userDetailMapper.toUserDetailDTO(profile);
    }
}
