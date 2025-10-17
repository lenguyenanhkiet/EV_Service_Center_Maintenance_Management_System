package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.PartsRequestDto;
import com.swp391.ev_service_center.entity.Parts;
import com.swp391.ev_service_center.repository.PartsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartsServiceImpl implements PartsService {

    @Autowired
    PartsRepository partsRepository;

    @Override
    public Parts createPart(PartsRequestDto requestDto) {
        partsRepository.findByCodePart(requestDto.getCodePart()).ifPresent(existing -> {
            throw new IllegalArgumentException("Part with this code already exists");
        });
        Parts part = new Parts();
        BeanUtils.copyProperties(requestDto, part);

        return partsRepository.save(part);
    }

    @Override
    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }

    @Override
    public Parts findByCode(String codePart) {
        return partsRepository.findByCodePart(codePart)
                .orElseThrow(() -> new RuntimeException("Part not found with code: " + codePart));
    }

    @Override
    public Parts updatePart(String codePart, PartsRequestDto dto) {
        Parts part = partsRepository.findByCodePart(codePart)
                .orElseThrow(() -> new EntityNotFoundException("Part not found with code: " + codePart));

        // Update fields
        part.setName(dto.getName());
        part.setUnit(dto.getUnit());
        part.setDescription(dto.getDescription());

        return partsRepository.save(part);
    }

    @Override
    public void deletePart(String codePart) {
        Parts part = partsRepository.findByCodePart(codePart)
                .orElseThrow(() -> new IllegalArgumentException("Part not found with code: " + codePart));

        partsRepository.delete(part);
    }
}
