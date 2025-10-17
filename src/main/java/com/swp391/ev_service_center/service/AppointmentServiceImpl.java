package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.AppointmentRequestDto;
import com.swp391.ev_service_center.dto.AppointmentResponseDto;
import com.swp391.ev_service_center.entity.Appointments;
import com.swp391.ev_service_center.entity.ServiceCenter;
import com.swp391.ev_service_center.entity.Vehicles;
import com.swp391.ev_service_center.repository.AppointmentRepository;
import com.swp391.ev_service_center.repository.ServiceCenterRepository;
import com.swp391.ev_service_center.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    VehicleRepository vehiclesRepository;
    @Autowired
    ServiceCenterRepository serviceCenterRepository;

    @Override
    public Appointments createAppointment(AppointmentRequestDto dto) {
        Vehicles vehicle = vehiclesRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        ServiceCenter center = serviceCenterRepository.findById(dto.getCenterId())
                .orElseThrow(() -> new EntityNotFoundException("Service center not found"));

        Appointments appointment = new Appointments();
        BeanUtils.copyProperties(dto, appointment);
        appointment.setVehicle(vehicle);
        appointment.setServiceCenter(center);

        Appointments saved = appointmentRepository.save(appointment);

        String invoiceNo = String.format("INV%04d", saved.getId());
        saved.setInvoiceNo(invoiceNo);

        return appointmentRepository.save(saved);
    }


    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        List<Appointments> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::convertToResponse)
                .toList();
    }

    private AppointmentResponseDto convertToResponse(Appointments entity) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getVehicle() != null) dto.setVehicleId(entity.getVehicle().getId());
        if (entity.getServiceCenter() != null) dto.setCenterId(entity.getServiceCenter().getId());
        return dto;
    }


    @Override
    public AppointmentResponseDto findByInvoiceNo(String invoiceNo) {
        Appointments appointment = appointmentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new RuntimeException("Appointment not found with invoiceNo: " + invoiceNo));

        return convertToResponse(appointment);
    }


    @Override
    public Appointments updateAppointment(String invoiceNo, AppointmentRequestDto dto) {
        Appointments appointment = appointmentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with invoiceNo: " + invoiceNo));

        if (!vehiclesRepository.existsById(dto.getVehicleId())) {
            throw new IllegalArgumentException("Vehicle with ID " + dto.getVehicleId() + " does not exist.");
        }

        if (!serviceCenterRepository.existsById(dto.getCenterId())) {
            throw new IllegalArgumentException("Service Center with ID " + dto.getCenterId() + " does not exist.");
        }

        BeanUtils.copyProperties(dto, appointment);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(String invoiceNo) {
        Appointments appointment = appointmentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with invoiceNo: " + invoiceNo));
        appointmentRepository.delete(appointment);
    }
}
