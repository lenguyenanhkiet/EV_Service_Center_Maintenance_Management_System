package com.swp391.ev_service_center.controller;

import com.swp391.ev_service_center.dto.ServiceCenterCreateRequestDto;
import com.swp391.ev_service_center.dto.ServiceCenterResponseDto;
import com.swp391.ev_service_center.entity.ServiceCenter;
import com.swp391.ev_service_center.service.ServiceCenterService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/centers")
@CrossOrigin
public class ServiceCenterController {
    @Autowired
    ServiceCenterService serviceCenterService;

    @PostMapping
    public ResponseEntity<ServiceCenterResponseDto> createCenter(@Valid @RequestBody ServiceCenterCreateRequestDto requestDto){
        ServiceCenter createdCenter = serviceCenterService.createServiceCenter(requestDto);
        ServiceCenterResponseDto centerResponseDto = new ServiceCenterResponseDto();
        BeanUtils.copyProperties(createdCenter, centerResponseDto);
        if(createdCenter.getStatus() != null){
            centerResponseDto.setStatus(createdCenter.getStatus().name());
        }
        return new ResponseEntity<>(centerResponseDto, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<ServiceCenterResponseDto>> findAll(){
//        List<ServiceCenter> centers = serviceCenterService.getAllServiceCenters();
//        BeanUtils.copyProperties(centers, centers);
//    }
}
