package com.swp391.ev_service_center.repository;

import com.swp391.ev_service_center.entity.ServiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceTypesRepository extends JpaRepository<ServiceTypes, Long> {
    Optional<ServiceTypes> findByCode(String code);
}
