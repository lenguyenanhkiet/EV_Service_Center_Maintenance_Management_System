package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VehicleRequestDto {
    private Long customerId;
    private String vin;
    private String color;
    private BigDecimal batteryCapacityKwh;
    private Integer year;
    private BigDecimal batteryHealthPercent;
    private Integer odometerKm;
    private String licensePlate;
    private String model;
}
