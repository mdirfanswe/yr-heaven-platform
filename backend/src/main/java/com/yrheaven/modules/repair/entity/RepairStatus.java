package com.yrheaven.modules.repair.entity;

public enum RepairStatus {
    RECEIVED,
    DIAGNOSING,
    WAITING_FOR_APPROVAL,
    WAITING_FOR_PARTS,
    REPAIR_IN_PROGRESS,
    TESTING,
    READY_FOR_PICKUP,
    DELIVERED,
    CANCELLED
}