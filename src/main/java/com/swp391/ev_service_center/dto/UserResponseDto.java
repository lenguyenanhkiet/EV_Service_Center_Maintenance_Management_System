package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private String role;
}
