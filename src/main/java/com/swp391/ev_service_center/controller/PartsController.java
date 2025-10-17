package com.swp391.ev_service_center.controller;

import com.swp391.ev_service_center.dto.PartsRequestDto;
import com.swp391.ev_service_center.entity.Parts;
import com.swp391.ev_service_center.service.PartsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin
public class PartsController {

    @Autowired
    PartsService partsService;

//    {
//        "codePart": "P006",
//            "name": "Tire Pressure Sensor",
//            "unit": "pcs",
//            "description": "TPMS sensor for accurate tire pressure monitoring"
//    }

    @PostMapping
    public ResponseEntity<Parts> createPart(@Valid @RequestBody PartsRequestDto dto) {
        Parts createdPart = partsService.createPart(dto);
        return new ResponseEntity<>(createdPart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Parts>> getAllParts() {
        List<Parts> parts = partsService.getAllParts();
        return new ResponseEntity<>(parts, HttpStatus.OK);
    }

    @GetMapping("/{codePart}")
    public ResponseEntity<Parts> getPartByCode(@PathVariable String codePart) {
        Parts part = partsService.findByCode(codePart);
        return new ResponseEntity<>(part, HttpStatus.OK);
    }

    @PutMapping("/{codePart}")
    public ResponseEntity<Parts> updatePart(@PathVariable String codePart, @Valid @RequestBody PartsRequestDto dto) {
        Parts updatedPart = partsService.updatePart(codePart, dto);
        return new ResponseEntity<>(updatedPart, HttpStatus.OK);
    }

    @DeleteMapping("/{codePart}")
    public ResponseEntity<String> deletePart(@PathVariable String codePart) {
        partsService.deletePart(codePart);
        return ResponseEntity.ok("Deleted part with code: " + codePart);
    }
}
