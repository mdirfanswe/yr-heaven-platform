package com.yrheaven.modules.repair.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "repair_jobs")
public class RepairJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_reference", nullable = false, unique = true, length = 30)
    private String jobReference;

    @Column(name = "tracking_token", nullable = false, unique = true, length = 64)
    private String trackingToken;

    @Column(name = "customer_name", nullable = false, length = 120)
    private String customerName;

    @Column(name = "customer_phone", nullable = false, length = 20)
    private String customerPhone;

    @Column(name = "customer_user_id")
    private Long customerUserId;

    @Column(name = "device_brand", nullable = false, length = 60)
    private String deviceBrand;

    @Column(name = "device_model", nullable = false, length = 100)
    private String deviceModel;

    @Column(name = "reported_issue", nullable = false, columnDefinition = "TEXT")
    private String reportedIssue;

    @Column(name = "condition_notes", columnDefinition = "TEXT")
    private String conditionNotes;

    // Internal-only fields: NEVER exposed in public tracking DTOs
    @Column(name = "diagnostic_findings", columnDefinition = "TEXT")
    private String diagnosticFindings;

    @Column(name = "internal_notes", columnDefinition = "TEXT")
    private String internalNotes;

    // Public-safe message written specifically for the customer
    @Column(name = "customer_visible_message", length = 500)
    private String customerVisibleMessage;

    @Column(name = "estimated_cost_min", precision = 10, scale = 2)
    private BigDecimal estimatedCostMin;

    @Column(name = "estimated_cost_max", precision = 10, scale = 2)
    private BigDecimal estimatedCostMax;

    @Column(name = "approved_quote", precision = 10, scale = 2)
    private BigDecimal approvedQuote;

    @Column(name = "final_charge", precision = 10, scale = 2)
    private BigDecimal finalCharge;

    @Column(name = "advance_paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal advancePaid = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 30)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false, length = 40)
    private RepairStatus currentStatus = RepairStatus.RECEIVED;

    @Column(name = "expected_completion_at")
    private Instant expectedCompletionAt;

    @Version
    @Column(nullable = false)
    private Long version = 0L;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getJobReference() {
        return jobReference;
    }

    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    public String getTrackingToken() {
        return trackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        this.trackingToken = trackingToken;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getReportedIssue() {
        return reportedIssue;
    }

    public void setReportedIssue(String reportedIssue) {
        this.reportedIssue = reportedIssue;
    }

    public String getConditionNotes() {
        return conditionNotes;
    }

    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }

    public String getDiagnosticFindings() {
        return diagnosticFindings;
    }

    public void setDiagnosticFindings(String diagnosticFindings) {
        this.diagnosticFindings = diagnosticFindings;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public String getCustomerVisibleMessage() {
        return customerVisibleMessage;
    }

    public void setCustomerVisibleMessage(String customerVisibleMessage) {
        this.customerVisibleMessage = customerVisibleMessage;
    }

    public BigDecimal getEstimatedCostMin() {
        return estimatedCostMin;
    }

    public void setEstimatedCostMin(BigDecimal estimatedCostMin) {
        this.estimatedCostMin = estimatedCostMin;
    }

    public BigDecimal getEstimatedCostMax() {
        return estimatedCostMax;
    }

    public void setEstimatedCostMax(BigDecimal estimatedCostMax) {
        this.estimatedCostMax = estimatedCostMax;
    }

    public BigDecimal getApprovedQuote() {
        return approvedQuote;
    }

    public void setApprovedQuote(BigDecimal approvedQuote) {
        this.approvedQuote = approvedQuote;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public BigDecimal getAdvancePaid() {
        return advancePaid;
    }

    public void setAdvancePaid(BigDecimal advancePaid) {
        this.advancePaid = advancePaid;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public RepairStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(RepairStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Instant getExpectedCompletionAt() {
        return expectedCompletionAt;
    }

    public void setExpectedCompletionAt(Instant expectedCompletionAt) {
        this.expectedCompletionAt = expectedCompletionAt;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}