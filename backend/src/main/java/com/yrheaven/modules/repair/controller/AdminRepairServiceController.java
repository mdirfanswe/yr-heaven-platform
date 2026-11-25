package com.yrheaven.modules.repair.controller;

import com.yrheaven.modules.repair.dto.RepairServiceRequest;
import com.yrheaven.modules.repair.dto.RepairServiceResponse;
import com.yrheaven.modules.repair.service.RepairServiceCatalogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/repair-services")
public class AdminRepairServiceController {

    private final RepairServiceCatalogService service;

    public AdminRepairServiceController(RepairServiceCatalogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RepairServiceResponse>> listAllServices() {
        return ResponseEntity.ok(service.getAllServicesForOwner());
    }

    @PostMapping
    public ResponseEntity<RepairServiceResponse> createService(@Valid @RequestBody RepairServiceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createService(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairServiceResponse> updateService(
            @PathVariable Long id,
            @Valid @RequestBody RepairServiceRequest request
    ) {
        return ResponseEntity.ok(service.updateService(id, request));
    }
}