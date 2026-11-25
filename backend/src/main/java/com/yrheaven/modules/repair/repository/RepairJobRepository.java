package com.yrheaven.modules.repair.repository;

import com.yrheaven.modules.repair.entity.RepairJob;
import com.yrheaven.modules.repair.entity.RepairStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairJobRepository extends JpaRepository<RepairJob, Long> {

    // Used by the public tracking endpoint — strictly by unguessable token
    Optional<RepairJob> findByTrackingToken(String trackingToken);

    boolean existsByTrackingToken(String trackingToken);

    boolean existsByJobReference(String jobReference);

    // Used by the owner dashboard with pagination
    Page<RepairJob> findByCurrentStatus(RepairStatus status, Pageable pageable);

    Page<RepairJob> findByJobReferenceContainingIgnoreCaseOrCustomerNameContainingIgnoreCaseOrCustomerPhoneContaining(
            String jobReference,
            String customerName,
            String customerPhone,
            Pageable pageable
    );
}