package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceTypesRequestDto {
    private String code;
    private String name;
    private String category;
    private String detailCategory;
    private BigDecimal basePrice;
    private Integer estimatedTime;
}
