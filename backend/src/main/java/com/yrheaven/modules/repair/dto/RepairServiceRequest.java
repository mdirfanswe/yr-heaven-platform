package com.yrheaven.modules.repair.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RepairServiceRequest(
        @NotBlank(message = "Service name is required")
        @Size(max = 150, message = "Service name must be under 150 characters")
        String serviceName,

        @NotBlank(message = "Category is required")
        @Size(max = 80, message = "Category must be under 80 characters")
        String category,

        @Size(max = 1000, message = "Description must be under 1000 characters")
        String description,

        @DecimalMin(value = "0.00", message = "Minimum price cannot be negative")
        BigDecimal priceMin,

        @DecimalMin(value = "0.00", message = "Maximum price cannot be negative")
        BigDecimal priceMax,

        @Size(max = 100, message = "Estimated turnaround text must be under 100 characters")
        String estimatedTurnaroundText,

        Boolean active,

        @Min(value = 0, message = "Display order cannot be negative")
        Integer displayOrder
) {
}