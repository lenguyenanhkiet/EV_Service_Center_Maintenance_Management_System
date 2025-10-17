package com.swp391.ev_service_center.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "parts")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Parts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_part", length = 64, nullable = false, unique = true)
    private String codePart;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 30, nullable = false)
    private String unit = "pcs";
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreatedDate
    @Column(nullable = false, updatable = false, name = "create_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false, name = "update_at")
    private LocalDateTime updatedAt;
}
