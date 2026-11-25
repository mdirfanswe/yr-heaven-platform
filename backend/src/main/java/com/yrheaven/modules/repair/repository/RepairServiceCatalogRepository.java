package com.yrheaven.modules.repair.repository;

import com.yrheaven.modules.repair.entity.RepairServiceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairServiceCatalogRepository extends JpaRepository<RepairServiceCatalog, Long> {

    // Used by public website: only active services, sorted nicely
    List<RepairServiceCatalog> findByActiveTrueOrderByDisplayOrderAscServiceNameAsc();

    // Used by owner dashboard: all services (including hidden/inactive ones)
    List<RepairServiceCatalog> findAllByOrderByDisplayOrderAscServiceNameAsc();
}