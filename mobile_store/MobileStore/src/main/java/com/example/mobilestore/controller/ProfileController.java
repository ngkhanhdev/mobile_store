package com.example.mobilestore.controller;
import com.example.mobilestore.dto.UserDetailDTO;
import com.example.mobilestore.dto.common.BaseResponse;
import com.example.mobilestore.service.UserDetailService;
import com.example.mobilestore.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserDetailService userDetailService;
    @GetMapping("/{username}")
    public ResponseEntity<BaseResponse<UserDetailDTO>> getUserDetailWithUsername(@PathVariable String username){
        return ResponseFactory.ok(userDetailService.getUserDetail(username));
    }
    @PostMapping()
    public ResponseEntity<BaseResponse<UserDetailDTO>> creatreProfile(@Valid @ModelAttribute(name = "userDetail") UserDetailDTO userDetailDTO,
                                                                      @RequestParam("avatar") MultipartFile avatar) throws IOException{
        UserDetailDTO userDetailDTO1 = userDetailService.createProfile(userDetailDTO, avatar);
        return ResponseFactory.ok(userDetailDTO1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserDetailDTO>> updateProfile(@Valid @ModelAttribute(name = "userdetail") UserDetailDTO userDetailDTO,
                                                                     @PathVariable String id,
                                                                     @RequestParam("avatar") MultipartFile avatar) throws IOException{
//        System.out.println("Profile: " +userDetailDTO.getFirstname());
//        System.out.println("Profile: " +userDetailDTO.getMiddlename());
//        System.out.println("Profile: " +userDetailDTO.getLastname());
//        System.out.println("Profile: " +userDetailDTO.getAddress());
//        System.out.println("Profile: " +userDetailDTO.getPhone());
//        System.out.println("Profile: " +userDetailDTO.getUserId());
//        System.out.println("Image: " + avatar.getOriginalFilename());


        return ResponseFactory.ok(userDetailService.updateUserDetail(id, userDetailDTO, avatar));
    }
}
