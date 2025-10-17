package com.swp391.ev_service_center.controller;

import com.swp391.ev_service_center.dto.VehicleRequestDto;
import com.swp391.ev_service_center.entity.Vehicles;
import com.swp391.ev_service_center.service.VehicleService;
import com.swp391.ev_service_center.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {

    @Autowired
    VehicleService vehiclesService;

    @PostMapping
    public ResponseEntity<Vehicles> createVehicle(@Valid @RequestBody VehicleRequestDto dto) {
        Vehicles vehicle = vehiclesService.createVehicle(dto);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicles>> getAllVehicles() {
        return new ResponseEntity<>(vehiclesService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Vehicles> getByVin(@PathVariable String vin) {
        return new ResponseEntity<>(vehiclesService.findByVin(vin), HttpStatus.OK);
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Vehicles> updateVehicle(@PathVariable String vin, @Valid @RequestBody VehicleRequestDto dto) {
        Vehicles updated = vehiclesService.updateVehicle(vin, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vin) {
        vehiclesService.deleteVehicle(vin);
        return ResponseEntity.ok("Vehicle with VIN " + vin + " deleted successfully.");
    }
}
