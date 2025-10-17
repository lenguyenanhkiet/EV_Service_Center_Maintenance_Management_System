package com.swp391.ev_service_center.controller;

import com.swp391.ev_service_center.dto.ServiceTypesRequestDto;
import com.swp391.ev_service_center.dto.ServiceTypesResponseDto;
import com.swp391.ev_service_center.entity.ServiceTypes;
import com.swp391.ev_service_center.service.ServiceTypesService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin
public class ServiceTypesController {

    @Autowired
    ServiceTypesService serviceTypeService;

//    {
//        "code": "SRV005",
//            "name": "Air Conditioning Service",
//            "category": "Cooling System",
//            "detailCategory": "Air Conditioning and Ventilation",
//            "basePrice": 700.00,
//            "estimatedTime": 60
//    }

    @PostMapping
    public ResponseEntity<ServiceTypesResponseDto> createServiceType(@Valid @RequestBody ServiceTypesRequestDto requestDto) {
        ServiceTypes createdService = serviceTypeService.createServiceType(requestDto);
        ServiceTypesResponseDto responseDto = new ServiceTypesResponseDto();
        BeanUtils.copyProperties(createdService, responseDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceTypesResponseDto>> getAllServiceTypes() {
        List<ServiceTypes> serviceTypes = serviceTypeService.getAllServiceTypes();
        List<ServiceTypesResponseDto> responseDtos = serviceTypes.stream().map(service -> {
            ServiceTypesResponseDto dto = new ServiceTypesResponseDto();
            BeanUtils.copyProperties(service, dto);
            return dto;
        }).toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ServiceTypesResponseDto> getByCode(@PathVariable String code) {
        ServiceTypes serviceType = serviceTypeService.findByCode(code);
        ServiceTypesResponseDto dto = new ServiceTypesResponseDto();
        BeanUtils.copyProperties(serviceType, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ServiceTypesResponseDto> updateServiceType(
            @PathVariable String code,
            @Valid @RequestBody ServiceTypesRequestDto requestDto) {
        ServiceTypes updatedService = serviceTypeService.updateServiceType(code, requestDto);
        ServiceTypesResponseDto responseDto = new ServiceTypesResponseDto();
        BeanUtils.copyProperties(updatedService, responseDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteServiceType(@PathVariable String code) {
        serviceTypeService.deleteServiceType(code);
        return ResponseEntity.ok("Service type with code " + code + " deleted successfully.");
    }
}
