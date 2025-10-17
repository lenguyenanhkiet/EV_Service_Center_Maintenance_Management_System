package com.swp391.ev_service_center.validation;

import com.swp391.ev_service_center.dto.RegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

   @Override
   public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
     RegistrationRequestDto accountDto = (RegistrationRequestDto) obj;
        return accountDto.getPassword().equals(accountDto.getConfirmPassword());
    }
}
