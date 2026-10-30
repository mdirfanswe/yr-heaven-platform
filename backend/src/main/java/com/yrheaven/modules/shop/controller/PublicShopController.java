package com.yrheaven.modules.shop.controller;

import com.yrheaven.modules.shop.dto.ShopPublicInfoDto;
import com.yrheaven.modules.shop.service.ShopSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/shop-info")
public class PublicShopController {

    private final ShopSettingsService shopSettingsService;

    public PublicShopController(ShopSettingsService shopSettingsService) {
        this.shopSettingsService = shopSettingsService;
    }

    @GetMapping
    public ResponseEntity<ShopPublicInfoDto> getShopInfo() {
        return ResponseEntity.ok(shopSettingsService.getPublicShopInfo());
    }
}