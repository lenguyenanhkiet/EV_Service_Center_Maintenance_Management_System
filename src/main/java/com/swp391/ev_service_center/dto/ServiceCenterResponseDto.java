package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCenterResponseDto {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
    private String status;
}
