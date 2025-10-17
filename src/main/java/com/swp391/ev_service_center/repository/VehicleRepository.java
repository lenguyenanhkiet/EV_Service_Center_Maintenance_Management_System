package com.swp391.ev_service_center.repository;

import com.swp391.ev_service_center.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicles, Long> {
    Optional<Vehicles> findByVin(String vin);
    Optional<Vehicles> findByLicensePlate(String licensePlate);
}
