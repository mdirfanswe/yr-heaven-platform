package com.yrheaven.modules.shop.repository;

import com.yrheaven.modules.shop.entity.ShopSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopSettingsRepository extends JpaRepository<ShopSettings, Long> {
}