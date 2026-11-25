package com.yrheaven.modules.repair.repository;

import com.yrheaven.modules.repair.entity.RepairStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairStatusHistoryRepository extends JpaRepository<RepairStatusHistory, Long> {

    // Returns the repair timeline in chronological order (oldest -> newest)
    List<RepairStatusHistory> findByRepairJobIdOrderByChangedAtAsc(Long repairJobId);
}