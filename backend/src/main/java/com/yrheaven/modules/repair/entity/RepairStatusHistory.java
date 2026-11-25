package com.yrheaven.modules.repair.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "repair_status_history")
public class RepairStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repair_job_id", nullable = false)
    private RepairJob repairJob;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status", length = 40)
    private RepairStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false, length = 40)
    private RepairStatus newStatus;

    @Column(name = "public_comment", length = 500)
    private String publicComment;

    @Column(name = "internal_comment", columnDefinition = "TEXT")
    private String internalComment;

    @Column(name = "changed_by_user_id")
    private Long changedByUserId;

    @Column(name = "changed_at", nullable = false, updatable = false)
    private Instant changedAt;

    @PrePersist
    protected void onCreate() {
        this.changedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public RepairJob getRepairJob() {
        return repairJob;
    }

    public void setRepairJob(RepairJob repairJob) {
        this.repairJob = repairJob;
    }

    public RepairStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(RepairStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public RepairStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(RepairStatus newStatus) {
        this.newStatus = newStatus;
    }

    public String getPublicComment() {
        return publicComment;
    }

    public void setPublicComment(String publicComment) {
        this.publicComment = publicComment;
    }

    public String getInternalComment() {
        return internalComment;
    }

    public void setInternalComment(String internalComment) {
        this.internalComment = internalComment;
    }

    public Long getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Long changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public Instant getChangedAt() {
        return changedAt;
    }
}