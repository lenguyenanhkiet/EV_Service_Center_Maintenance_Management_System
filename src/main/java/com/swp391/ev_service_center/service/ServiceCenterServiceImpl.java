package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.ServiceCenterCreateRequestDto;
import com.swp391.ev_service_center.entity.ServiceCenter;
import com.swp391.ev_service_center.repository.ServiceCenterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {

    @Autowired
    ServiceCenterRepository serviceCenterRepository;
    @Override
    public ServiceCenter createServiceCenter(ServiceCenterCreateRequestDto requestDto) {
        // Kiểm tra ServiceCode đã tồn tại hay chưa
        serviceCenterRepository.findByCode(requestDto.getCode()).ifPresent(serviceCenter -> {
            throw new IllegalArgumentException("Service center already exists");
        });
        // Chuyển dữ liệu DTO -> Entity để lưu trữ
        ServiceCenter serviceCenter = new ServiceCenter();
        // Set status cho service
        serviceCenter.setStatus(ServiceCenter.CenterStatus.Active);
        BeanUtils.copyProperties(requestDto, serviceCenter);
        // Dùng repo để lưu Entity và db
        return serviceCenterRepository.save(serviceCenter);
    }

    @Override
    public List<ServiceCenter> getAllServiceCenters() {
       return serviceCenterRepository.findAll();
    }
}
