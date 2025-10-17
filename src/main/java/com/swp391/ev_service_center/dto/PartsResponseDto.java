package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PartsResponseDto {
    private Long id;
    private String codePart;
    private String name;
    private String unit;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
