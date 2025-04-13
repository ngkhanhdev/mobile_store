package com.example.mobilestore.utils;

import com.example.mobilestore.dto.common.BaseResponse;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {
    private ResponseFactory(){}
    public static <T> ResponseEntity<BaseResponse<T>> ok(T data){
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(data);
        response.setStatus("200");
        response.setDescription("Success");

        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<BaseResponse<Void>> deleteSuccess() {
        BaseResponse<Void> response = new BaseResponse<>();
        response.setStatus("200");
        response.setDescription("Delete successful");
        return ResponseEntity.ok(response);
    }
//    public static ResponseEntity<String> badRequest(BindingResult result) {
//        List<String> errors = result.getFieldErrors().stream()
//                .map(FieldError::getDefaultMessage)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.badRequest().body("Validation errors: " + errors);
//    }
}
