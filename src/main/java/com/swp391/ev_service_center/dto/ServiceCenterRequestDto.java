package com.swp391.ev_service_center.dto;

import com.swp391.ev_service_center.entity.ServiceCenter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
@Setter
public class ServiceCenterRequestDto {
    private String code;
    private String name;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
    private LocalTime openTime;
    private LocalTime closeTime;
    private ServiceCenter.CenterStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
