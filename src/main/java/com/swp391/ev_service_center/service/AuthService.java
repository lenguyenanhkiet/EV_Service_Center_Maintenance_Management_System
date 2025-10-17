package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.LoginRequestDto;
import com.swp391.ev_service_center.dto.RegistrationRequestDto;
import com.swp391.ev_service_center.entity.User;
import jakarta.validation.Valid;

public interface AuthService {
     User register(RegistrationRequestDto requestDto);
     String login(LoginRequestDto requestDto);
}
