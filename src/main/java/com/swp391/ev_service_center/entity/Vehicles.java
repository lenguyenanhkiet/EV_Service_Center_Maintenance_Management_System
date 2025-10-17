package com.swp391.ev_service_center.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(nullable = false, unique = true, length = 20)
    private String vin; // 17 characters
    @Column(length = 50)
    private String color;
    @Column(name = "battery_capacity_kwh", precision = 12, scale = 2)
    private BigDecimal batteryCapacityKwh;
    @Column(nullable = false)
    private Integer year;
    @Column(name = "battery_health_percent", precision = 12, scale = 2)
    private BigDecimal batteryHealthPercent = new BigDecimal("100.00");
    @Column(name = "odometer_km")
    private Integer odometerKm = 0;
    @Column(nullable = false, unique = true, length = 20)
    private String licensePlate;
    @Column(length = 64)
    private String model;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
