package com.yrheaven.modules.repair.controller;

import com.yrheaven.modules.repair.dto.RepairServiceResponse;
import com.yrheaven.modules.repair.service.RepairServiceCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/repair-services")
public class PublicRepairServiceController {

    private final RepairServiceCatalogService service;

    public PublicRepairServiceController(RepairServiceCatalogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RepairServiceResponse>> listActiveServices() {
        return ResponseEntity.ok(service.getPublicActiveServices());
    }
}