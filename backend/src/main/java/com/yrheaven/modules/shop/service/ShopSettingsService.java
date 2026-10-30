package com.yrheaven.modules.shop.service;

import com.yrheaven.modules.shop.dto.ShopPublicInfoDto;
import com.yrheaven.modules.shop.entity.ShopSettings;
import com.yrheaven.modules.shop.repository.ShopSettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopSettingsService {

    private static final Long DEFAULT_SHOP_ID = 1L;

    private final ShopSettingsRepository shopSettingsRepository;

    // Constructor Injection (Best practice over @Autowired on fields)
    public ShopSettingsService(ShopSettingsRepository shopSettingsRepository) {
        this.shopSettingsRepository = shopSettingsRepository;
    }

    @Transactional(readOnly = true)
    public ShopPublicInfoDto getPublicShopInfo() {
        ShopSettings settings = shopSettingsRepository.findById(DEFAULT_SHOP_ID)
                .orElseThrow(() -> new IllegalStateException("Shop settings row (ID=1) is missing from database."));

        // Build a ready-to-click WhatsApp deep link with India country code (91)
        String cleanPhone = settings.getPhoneWhatsapp().replaceAll("[^0-9]", "");
        String whatsappUrl = "https://wa.me/91" + cleanPhone
                + "?text=Hello%20YR%20Heaven%20Mobile%20Hub%2C%20I%20have%20an%20enquiry.";

        return new ShopPublicInfoDto(
                settings.getBusinessName(),
                settings.getAddressLine(),
                settings.getPhoneWhatsapp(),
                whatsappUrl,
                settings.getBusyStatus(),
                settings.getOpeningHoursText(),
                settings.getHolidayNotice(),
                settings.getRepairEstimateDisclaimer(),
                settings.getRepairPolicyNote(),
                settings.getPrintingPolicyNote(),
                settings.getUpdatedAt()
        );
    }
}