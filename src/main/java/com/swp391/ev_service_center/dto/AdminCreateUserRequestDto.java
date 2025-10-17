package com.swp391.ev_service_center.dto;

import com.swp391.ev_service_center.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AdminCreateUserRequestDto {
    @NotBlank(message = "Full name is required")
    private String fullName;
    @Email(message = "Email invalid format")
    private String email;
    @NotBlank(message = "Pass word is required")
    @Pattern(regexp = "^(?=.[A-Za-z])(?=.\\d)(?=.*[!@#<span>%^&*()_+\\-={}:;'<>,.?/]).{6,}</span>$")
    @Size(min = 6, message = "Password must be as least 6 characters")
    private String password;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^(0\\d{9}|84\\d{9})$", message = "Invalid Vietnamese phone number format")
    private String phone;
    @NotBlank(message = "Role is required")
    private String role;
    private Long centerId;
    private String username;
    private String gender;
    private LocalDate dob;
    private boolean isActive =true;
}
