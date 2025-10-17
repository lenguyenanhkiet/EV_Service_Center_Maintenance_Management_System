package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String role;
}
