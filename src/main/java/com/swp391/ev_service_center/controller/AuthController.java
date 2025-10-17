package com.swp391.ev_service_center.controller;


import com.swp391.ev_service_center.dto.LoginRequestDto;
import com.swp391.ev_service_center.dto.LoginResponseDto;
import com.swp391.ev_service_center.dto.RegistrationRequestDto;
import com.swp391.ev_service_center.dto.UserResponseDto;
import com.swp391.ev_service_center.entity.User;
import com.swp391.ev_service_center.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegistrationRequestDto requestDto) {
        // Gọi method register
        User registerUser = authService.register(requestDto);
        UserResponseDto responseDto = new UserResponseDto();
        BeanUtils.copyProperties(registerUser,responseDto);
        if(registerUser.getRole() != null){
            responseDto.setRole(registerUser.getRole().name());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        String token = authService.login(requestDto);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getProfile(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        if(currentUser != null){
            System.out.println("currentUser:" + currentUser);
        }else{
            System.out.println("currentUser is null");
        }
        UserResponseDto responseDto = new UserResponseDto();
        BeanUtils.copyProperties(currentUser, responseDto);
        responseDto.setRole(currentUser.getRole().name());

        return ResponseEntity.ok(responseDto);
    }
}
