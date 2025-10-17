package com.swp391.ev_service_center.dto;

import com.swp391.ev_service_center.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class RegistrationRequestDto {
    @NotBlank(message = "Full name is required")
    private String fullName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
//    @Pattern(regexp = "^(?=.[A-Za-z])(?=.\\d)(?=.*[!@#<span>%^&*()_+\\-={}:;'<>,.?/]).{6,}</span>$")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
    @Pattern(regexp = "^(0\\d{9}|84\\d{9})$", message = "Invalid Vietnamese phone number format")
    @NotBlank(message = "Phone number is required")
    private String phone;
}
