package com.swp391.ev_service_center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartsRequestDto {
    private String codePart;
    private String name;
    private String unit;
    private String description;
}
