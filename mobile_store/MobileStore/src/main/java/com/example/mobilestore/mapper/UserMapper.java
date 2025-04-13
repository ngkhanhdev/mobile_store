package com.example.mobilestore.mapper;

import com.example.mobilestore.dto.UserDTO;
import com.example.mobilestore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    @Mapping(source = "role.roleName" , target = "roleName")
    UserDTO toUserDTO(User user);

    @Mapping(source = "roleName", target = "role.roleName")
    User toUser(UserDTO userDTO);
}
