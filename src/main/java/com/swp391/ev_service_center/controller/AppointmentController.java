package com.swp391.ev_service_center.controller;

import com.swp391.ev_service_center.dto.AppointmentRequestDto;
import com.swp391.ev_service_center.dto.AppointmentResponseDto;
import com.swp391.ev_service_center.entity.Appointments;
import com.swp391.ev_service_center.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

//    {
//        "vehicleId": 1,
//            "centerId": 2,
//            "scheduledAt": "2025-10-17T09:00:00",
//            "durationMinutes": 45,
//            "endAt": "2025-10-17T09:45:00",
//            "laborTotal": 300.00,
//            "partsTotal": 150.00,
//            "discount": 20.00,
//            "tax": 10.00,
//            "grandTotal": 440.00,
//            "status": "PENDING",
//            "billingStatus": "UNBILLED"
//    }


    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@Valid @RequestBody AppointmentRequestDto dto) {
        Appointments created = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{invoiceNo}")
    public ResponseEntity<AppointmentResponseDto> getByInvoice(@PathVariable String invoiceNo) {
        AppointmentResponseDto appointment = appointmentService.findByInvoiceNo(invoiceNo);
        AppointmentResponseDto dto = new AppointmentResponseDto();
        BeanUtils.copyProperties(appointment, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{invoiceNo}")
    public ResponseEntity<AppointmentResponseDto> update(@PathVariable String invoiceNo,
                                                         @Valid @RequestBody AppointmentRequestDto dto) {
        Appointments updated = appointmentService.updateAppointment(invoiceNo, dto);
        AppointmentResponseDto response = new AppointmentResponseDto();
        BeanUtils.copyProperties(updated, response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{invoiceNo}")
    public ResponseEntity<String> delete(@PathVariable String invoiceNo) {
        appointmentService.deleteAppointment(invoiceNo);
        return ResponseEntity.ok("Deleted appointment with invoiceNo: " + invoiceNo);
    }
}
