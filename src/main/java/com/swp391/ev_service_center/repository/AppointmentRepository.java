package com.swp391.ev_service_center.repository;

import com.swp391.ev_service_center.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
    Optional<Appointments> findByInvoiceNo(String invoiceNo);
}
