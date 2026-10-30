package com.yrheaven.modules.shop.dto;

import com.yrheaven.modules.shop.entity.ShopBusyStatus;

import java.time.Instant;

public record ShopPublicInfoDto(
        String businessName,
        String addressLine,
        String phoneWhatsapp,
        String whatsappChatUrl,
        ShopBusyStatus busyStatus,
        String openingHoursText,
        String holidayNotice,
        String repairEstimateDisclaimer,
        String repairPolicyNote,
        String printingPolicyNote,
        Instant updatedAt
) {
}