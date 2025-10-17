package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.VehicleRequestDto;
import com.swp391.ev_service_center.entity.Vehicles;
import com.swp391.ev_service_center.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehiclesRepository;

    @Override
    public Vehicles createVehicle(VehicleRequestDto requestDto) {
        vehiclesRepository.findByVin(requestDto.getVin()).ifPresent(v -> {
            throw new IllegalArgumentException("Vehicle with this VIN already exists");
        });
        Vehicles vehicle = new Vehicles();
        BeanUtils.copyProperties(requestDto, vehicle);
        return vehiclesRepository.save(vehicle);
    }

    @Override
    public List<Vehicles> getAllVehicles() {
        return vehiclesRepository.findAll();
    }

    @Override
    public Vehicles findByVin(String vin) {
        return vehiclesRepository.findByVin(vin)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with VIN: " + vin));
    }

    @Override
    public Vehicles updateVehicle(String vin, VehicleRequestDto dto) {
        Vehicles vehicle = vehiclesRepository.findByVin(vin)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with VIN: " + vin));
        BeanUtils.copyProperties(dto, vehicle, "id", "createdAt");
        return vehiclesRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(String vin) {
        Vehicles vehicle = vehiclesRepository.findByVin(vin)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with VIN: " + vin));
        vehiclesRepository.delete(vehicle);
    }
}
