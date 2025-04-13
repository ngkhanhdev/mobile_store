package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.UserDetailDTO;
import com.example.mobilestore.entity.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserDetailMapper {
    @Mapping(source = "user.id", target = "userId")
    UserDetailDTO toUserDetailDTO(UserDetail userDetail);
    UserDetail toUserDetail(UserDetailDTO userDetailDTO);
}
