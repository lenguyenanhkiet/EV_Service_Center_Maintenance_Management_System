package com.swp391.ev_service_center.repository;

import com.swp391.ev_service_center.entity.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PartsRepository extends JpaRepository<Parts, Long> {
    Optional<Parts> findByCodePart(String codePart);
}
