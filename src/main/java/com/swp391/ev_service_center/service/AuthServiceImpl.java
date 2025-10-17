package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.LoginRequestDto;
import com.swp391.ev_service_center.dto.RegistrationRequestDto;
import com.swp391.ev_service_center.entity.User;
import com.swp391.ev_service_center.exception.EmailAlreadyExistsException;
import com.swp391.ev_service_center.exception.PhoneAlreadyExistsException;
import com.swp391.ev_service_center.exception.ResourceNotFoundException;
import com.swp391.ev_service_center.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Override
    public User register(@Valid RegistrationRequestDto requestDto) {
        // Kiểm tra email đã tồn tại hay chưa
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        User newUser = new User();
        BeanUtils.copyProperties(requestDto, newUser);
        // Ma hoa password
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        // Set role
        newUser.setRole(User.Role.CUSTOMER);
        // Set active account
        newUser.setActive(true);
        return userRepository.save(newUser);
    }

    @Override
    public String login(LoginRequestDto requestDto) {
        // Xác thực người dùng
        // 1. Tìm kiếm username / email
        // 2. So sánh password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getLoginIdentifier(),
                        requestDto.getPassword()
                )
        );
        // Sau khi hợp lệ get thông tin người dùng tiếp tục
        var user = userRepository.findByEmail(requestDto.getLoginIdentifier())
                .or(() -> userRepository.findByUsername(requestDto.getLoginIdentifier()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid identifier or password!"));
        // Sau đó return generate jwtToken
        return jwtService.generateToken(user);
    }


}
