package com.example.mobilestore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface CloudinaryService {
    String getImageUrl(String publicId) throws IOException;
    Map<String, Object> uploadImage(MultipartFile file) throws IOException;
    Map<String, Object> updateImage(MultipartFile file, String publicId) throws IOException;



    void deleteImage(String publicId) throws IOException;
}