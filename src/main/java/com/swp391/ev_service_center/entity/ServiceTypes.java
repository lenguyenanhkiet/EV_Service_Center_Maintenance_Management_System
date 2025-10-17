package com.swp391.ev_service_center.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_types")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ServiceTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String code;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 100)
    private String category;
    @Column(length = 255)
    private String detailCategory;
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal basePrice;
    @Column(nullable = false)
    private Integer estimatedTime;
    @CreatedDate
    @Column(nullable = false, name = "create_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false, name = "update_at")
    private LocalDateTime updatedAt;
}
