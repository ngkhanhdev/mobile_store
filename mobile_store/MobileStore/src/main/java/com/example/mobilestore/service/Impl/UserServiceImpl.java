package com.example.mobilestore.service.Impl;

import com.example.mobilestore.dto.JwtDTO;
import com.example.mobilestore.dto.UserDTO;
import com.example.mobilestore.entity.Role;
import com.example.mobilestore.entity.User;
import com.example.mobilestore.exception.AlreadyExistsException;
import com.example.mobilestore.exception.InvalidCredentialsException;
import com.example.mobilestore.mapper.UserMapper;
import com.example.mobilestore.repository.RoleRepository;
import com.example.mobilestore.repository.UserRepository;
import com.example.mobilestore.security.CustomUserDetails;
import com.example.mobilestore.security.JwtTokenProvider;
import com.example.mobilestore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO register(UserDTO user) {
        if (user.getRoleName()==null){
            user.setRoleName("user");
        } else user.setRoleName(user.getRoleName());
//        System.out.println("User : " + user.getUsername());
//        System.out.println("User : " + user.getPassword());
//        System.out.println("User : " + user.getRoleName());


        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("User", "username", user.getUsername());
        }
        User userRegister = new User();
        userRegister.setPassword(passwordEncoder.encode(user.getPassword()));
        userRegister.setUsername(user.getUsername());

        String roleName = user.getRoleName();

        Role role = roleRepository.findByRoleName(roleName);
        userRegister.setRole(role);

        User saveUserRegister = userRepository.save(userRegister);
        user.setId(saveUserRegister.getId());
        user.setCreateAt(saveUserRegister.getCreateAt());
        user.setPassword(userRegister.getPassword());

        return user;
    }

    @Override
    public JwtDTO login(UserDTO user) {
//        System.out.println("username123: " + user.getUsername());
//        System.out.println("password: " + user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Retrieve user details from the authenticated token
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Generate JWT token
            String accessToken = jwtTokenProvider.generateToken(userDetails);
            Date expriedDate = jwtTokenProvider.extractExpiration(accessToken);

            return JwtDTO.builder()
                    .accessToken(accessToken)
                    .expiredIn(expriedDate)
                    .build();
        }catch (AuthenticationException e){
            log.error("Wrong username or password {}", e.getMessage(), e);
            throw new InvalidCredentialsException("Wrong username or password");
        }

    }

    @Override
    public Page<UserDTO> getAllUser(Pageable pageable) {

        return userRepository.findAll(pageable)
                .map(userMapper::toUserDTO);
//        return null;
    }

}
