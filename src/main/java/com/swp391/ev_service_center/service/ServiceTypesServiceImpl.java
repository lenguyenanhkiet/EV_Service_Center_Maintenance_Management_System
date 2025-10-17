package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.ServiceTypesRequestDto;
import com.swp391.ev_service_center.entity.ServiceTypes;
import com.swp391.ev_service_center.repository.ServiceTypesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypesServiceImpl implements ServiceTypesService {

    @Autowired
    ServiceTypesRepository serviceTypesRepository;

    @Override
    public ServiceTypes createServiceType(ServiceTypesRequestDto requestDto) {
        // Kiểm tra code đã tồn tại hay chưa
        serviceTypesRepository.findByCode(requestDto.getCode()).ifPresent(existing -> {
            throw new IllegalArgumentException("Service type with this code already exists");
        });

        // Chuyển DTO sang Entity
        ServiceTypes serviceType = new ServiceTypes();
        BeanUtils.copyProperties(requestDto, serviceType);

        return serviceTypesRepository.save(serviceType);
    }

    @Override
    public List<ServiceTypes> getAllServiceTypes() {
        return serviceTypesRepository.findAll();
    }

    @Override
    public ServiceTypes findByCode(String code) {
        return serviceTypesRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Service Type not found with code: " + code));
    }

    @Override
    public ServiceTypes updateServiceType(String code, ServiceTypesRequestDto dto) {
        ServiceTypes serviceType = serviceTypesRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Service Type not found with code: " + code));

        // Cập nhật các trường
        serviceType.setName(dto.getName());
        serviceType.setCategory(dto.getCategory());
        serviceType.setDetailCategory(dto.getDetailCategory());
        serviceType.setBasePrice(dto.getBasePrice());
        serviceType.setEstimatedTime(dto.getEstimatedTime());

        return serviceTypesRepository.save(serviceType);
    }

    @Override
    public void deleteServiceType(String code) {
        ServiceTypes serviceType = serviceTypesRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Service Type not found with code: " + code));

        serviceTypesRepository.delete(serviceType);
    }
}
