package com.yrheaven.modules.repair.service;

import com.yrheaven.common.exception.BusinessException;
import com.yrheaven.modules.repair.dto.RepairServiceRequest;
import com.yrheaven.modules.repair.dto.RepairServiceResponse;
import com.yrheaven.modules.repair.entity.RepairServiceCatalog;
import com.yrheaven.modules.repair.repository.RepairServiceCatalogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RepairServiceCatalogService {

    private final RepairServiceCatalogRepository repository;

    public RepairServiceCatalogService(RepairServiceCatalogRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<RepairServiceResponse> getPublicActiveServices() {
        return repository.findByActiveTrueOrderByDisplayOrderAscServiceNameAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RepairServiceResponse> getAllServicesForOwner() {
        return repository.findAllByOrderByDisplayOrderAscServiceNameAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public RepairServiceResponse createService(RepairServiceRequest request) {
        validatePriceRange(request.priceMin(), request.priceMax());

        RepairServiceCatalog entity = new RepairServiceCatalog();
        applyRequest(entity, request);

        return toResponse(repository.save(entity));
    }

    @Transactional
    public RepairServiceResponse updateService(Long id, RepairServiceRequest request) {
        validatePriceRange(request.priceMin(), request.priceMax());

        RepairServiceCatalog entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Repair service not found with ID: " + id, HttpStatus.NOT_FOUND));

        applyRequest(entity, request);
        return toResponse(repository.save(entity));
    }

    private void validatePriceRange(BigDecimal priceMin, BigDecimal priceMax) {
        if (priceMin != null && priceMax != null && priceMin.compareTo(priceMax) > 0) {
            throw new BusinessException(
                    "Minimum estimated price cannot be greater than maximum estimated price.",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private void applyRequest(RepairServiceCatalog entity, RepairServiceRequest request) {
        entity.setServiceName(request.serviceName().trim());
        entity.setCategory(request.category().trim());
        entity.setDescription(request.description() != null ? request.description().trim() : null);
        entity.setPriceMin(request.priceMin());
        entity.setPriceMax(request.priceMax());
        entity.setEstimatedTurnaroundText(
                request.estimatedTurnaroundText() != null ? request.estimatedTurnaroundText().trim() : null
        );
        entity.setActive(request.active() != null ? request.active() : true);
        entity.setDisplayOrder(request.displayOrder() != null ? request.displayOrder() : 0);
    }

    private RepairServiceResponse toResponse(RepairServiceCatalog entity) {
        return new RepairServiceResponse(
                entity.getId(),
                entity.getServiceName(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getPriceMin(),
                entity.getPriceMax(),
                entity.getEstimatedTurnaroundText(),
                entity.isActive(),
                entity.getDisplayOrder(),
                entity.getUpdatedAt()
        );
    }
}