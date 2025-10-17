package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.VehicleRequestDto;
import com.swp391.ev_service_center.entity.Vehicles;

import java.util.List;

public interface VehicleService {
    Vehicles createVehicle(VehicleRequestDto requestDto);
    List<Vehicles> getAllVehicles();
    Vehicles findByVin(String vin);
    Vehicles updateVehicle(String vin, VehicleRequestDto dto);
    void deleteVehicle(String vin);
}
