package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.ServiceCenterCreateRequestDto;
import com.swp391.ev_service_center.entity.ServiceCenter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ServiceCenterService {
    public ServiceCenter createServiceCenter(ServiceCenterCreateRequestDto requestDto);
    List<ServiceCenter> getAllServiceCenters();
}
