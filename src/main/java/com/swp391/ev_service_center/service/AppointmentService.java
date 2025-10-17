package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.dto.AppointmentRequestDto;
import com.swp391.ev_service_center.dto.AppointmentResponseDto;
import com.swp391.ev_service_center.entity.Appointments;

import java.util.List;

public interface AppointmentService {
    Appointments createAppointment(AppointmentRequestDto dto);
    List<AppointmentResponseDto> getAllAppointments();
    AppointmentResponseDto findByInvoiceNo(String invoiceNo);
    Appointments updateAppointment(String invoiceNo, AppointmentRequestDto dto);
    void deleteAppointment(String invoiceNo);
}
