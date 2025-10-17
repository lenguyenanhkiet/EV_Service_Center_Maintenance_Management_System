package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceTypesResponseDto {
    private Long id;
    private String code;
    private String name;
    private String category;
    private String detailCategory;
    private BigDecimal basePrice;
    private Integer estimatedTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
