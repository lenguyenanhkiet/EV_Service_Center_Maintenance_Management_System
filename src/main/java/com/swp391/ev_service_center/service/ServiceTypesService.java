package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.ServiceTypesRequestDto;
import com.swp391.ev_service_center.entity.ServiceTypes;

import java.util.List;

public interface ServiceTypesService {

    public ServiceTypes createServiceType(ServiceTypesRequestDto requestDto);

    List<ServiceTypes> getAllServiceTypes();

    ServiceTypes findByCode(String code);

    public ServiceTypes updateServiceType(String code, ServiceTypesRequestDto dto);

    void deleteServiceType(String code);
}
