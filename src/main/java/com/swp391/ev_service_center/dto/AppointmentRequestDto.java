package com.swp391.ev_service_center.dto;

import com.swp391.ev_service_center.entity.Appointments;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequestDto {
    private Long vehicleId;
    private Long centerId;
    private LocalDateTime scheduledAt;
    private Integer durationMinutes;
    private LocalDateTime endAt;
    private BigDecimal laborTotal;
    private BigDecimal partsTotal;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal grandTotal;
    private Appointments.Status status;
    private Appointments.BillingStatus billingStatus;
}
