package com.swp391.ev_service_center.exception;

import com.swp391.ev_service_center.dto.RegistrationRequestDto;

public class PhoneAlreadyExistsException extends DataConflictException {
    public PhoneAlreadyExistsException(String msg) {
        super(msg);
    }
}
