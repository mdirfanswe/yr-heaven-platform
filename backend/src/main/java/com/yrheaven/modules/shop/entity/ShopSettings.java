package com.yrheaven.modules.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "shop_settings")
public class ShopSettings {

    // We use a fixed ID = 1L because there is only one physical shop
    @Id
    private Long id;

    @Column(name = "business_name", nullable = false, length = 150)
    private String businessName;

    @Column(name = "address_line", nullable = false, length = 255)
    private String addressLine;

    @Column(name = "phone_whatsapp", nullable = false, length = 20)
    private String phoneWhatsapp;

    // IMPORTANT: Always use EnumType.STRING!
    // Default is ORDINAL (0, 1, 2), which corrupts data if enum order changes later.
    @Enumerated(EnumType.STRING)
    @Column(name = "busy_status", nullable = false, length = 30)
    private ShopBusyStatus busyStatus = ShopBusyStatus.NORMAL;

    @Column(name = "opening_hours_text", length = 255)
    private String openingHoursText;

    @Column(name = "holiday_notice", length = 255)
    private String holidayNotice;

    @Column(name = "repair_estimate_disclaimer", nullable = false, columnDefinition = "TEXT")
    private String repairEstimateDisclaimer;

    @Column(name = "repair_policy_note", columnDefinition = "TEXT")
    private String repairPolicyNote;

    @Column(name = "printing_policy_note", columnDefinition = "TEXT")
    private String printingPolicyNote;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPhoneWhatsapp() {
        return phoneWhatsapp;
    }

    public void setPhoneWhatsapp(String phoneWhatsapp) {
        this.phoneWhatsapp = phoneWhatsapp;
    }

    public ShopBusyStatus getBusyStatus() {
        return busyStatus;
    }

    public void setBusyStatus(ShopBusyStatus busyStatus) {
        this.busyStatus = busyStatus;
    }

    public String getOpeningHoursText() {
        return openingHoursText;
    }

    public void setOpeningHoursText(String openingHoursText) {
        this.openingHoursText = openingHoursText;
    }

    public String getHolidayNotice() {
        return holidayNotice;
    }

    public void setHolidayNotice(String holidayNotice) {
        this.holidayNotice = holidayNotice;
    }

    public String getRepairEstimateDisclaimer() {
        return repairEstimateDisclaimer;
    }

    public void setRepairEstimateDisclaimer(String repairEstimateDisclaimer) {
        this.repairEstimateDisclaimer = repairEstimateDisclaimer;
    }

    public String getRepairPolicyNote() {
        return repairPolicyNote;
    }

    public void setRepairPolicyNote(String repairPolicyNote) {
        this.repairPolicyNote = repairPolicyNote;
    }

    public String getPrintingPolicyNote() {
        return printingPolicyNote;
    }

    public void setPrintingPolicyNote(String printingPolicyNote) {
        this.printingPolicyNote = printingPolicyNote;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}