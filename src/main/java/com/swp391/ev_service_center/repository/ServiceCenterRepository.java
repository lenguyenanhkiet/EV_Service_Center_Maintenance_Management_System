package com.swp391.ev_service_center.repository;

import com.swp391.ev_service_center.entity.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Long> {
    Optional<ServiceCenter> findByCode(String code);

}
