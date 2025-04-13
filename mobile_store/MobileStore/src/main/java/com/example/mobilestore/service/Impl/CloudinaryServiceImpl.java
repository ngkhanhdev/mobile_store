package com.example.mobilestore.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.mobilestore.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;
    @Value("${cloudinary.folderName}")
    private String folderName;
    @Override
    public String getImageUrl(String publicId) throws IOException {
        String publicIdWithFolder = folderName + "/" + publicId;
        try {
            Map resource = cloudinary.api().resource(publicIdWithFolder, ObjectUtils.emptyMap());
            return (String) resource.get("secure_url");
        } catch (Exception e) {
            // Chuyển đổi Exception thành IOException
            throw new IOException("Failed to get image URL", e);
        }
    }

    @Override
    public Map<String, Object> updateImage(MultipartFile file, String publicId) throws IOException {
        // Xóa ảnh cũ
        deleteImage(publicId);

        // Tải ảnh mới lên với cùng publicId (thay thế ảnh cũ)
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("public_id", folderName + "/" + publicId, "folder", folderName));

        return  uploadResult;
    }

    @Override
    public Map<String, Object> uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", folderName));
        return uploadResult;
    }

    @Override
    public void deleteImage(String publicId) throws IOException {
        String publicIdWithFolder = folderName + "/" + publicId;
        cloudinary.uploader().destroy(publicIdWithFolder, ObjectUtils.emptyMap());
    }

}
