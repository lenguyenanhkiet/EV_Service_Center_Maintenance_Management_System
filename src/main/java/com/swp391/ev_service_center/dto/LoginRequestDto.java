package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    //loginIdentifier có thể nhận email or username
    private String loginIdentifier;
    private String password;
}
