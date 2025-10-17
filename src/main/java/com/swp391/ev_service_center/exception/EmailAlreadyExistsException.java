package com.swp391.ev_service_center.exception;



public class EmailAlreadyExistsException extends DataConflictException {
    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
