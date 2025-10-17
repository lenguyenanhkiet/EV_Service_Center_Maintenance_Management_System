package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.PartsRequestDto;
import com.swp391.ev_service_center.entity.Parts;

import java.util.List;

public interface PartsService {
    Parts createPart(PartsRequestDto requestDto);
    List<Parts> getAllParts();
    Parts findByCode(String codePart);
    Parts updatePart(String codePart, PartsRequestDto dto);
    void deletePart(String codePart);
}
