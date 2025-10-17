package com.swp391.ev_service_center.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name ="service_center")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class )
public class ServiceCenter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column(name ="open_time")
    private LocalTime openTime;
    @Column(name ="close_time")
    private LocalTime closeTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CenterStatus status;
    @CreatedDate
    @Column(nullable = false, name ="created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, name ="update_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public enum CenterStatus {
        Active, InActive
    }
}
