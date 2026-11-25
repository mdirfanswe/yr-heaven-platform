package com.yrheaven.modules.repair.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RepairServiceResponse(
        Long id,
        String serviceName,
        String category,
        String description,
        BigDecimal priceMin,
        BigDecimal priceMax,
        String estimatedTurnaroundText,
        boolean active,
        int displayOrder,
        Instant updatedAt
) {
}