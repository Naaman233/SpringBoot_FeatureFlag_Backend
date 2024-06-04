package com.example.spring_boot_feature_flag_demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring_boot_feature_flag_demo.config.FeatureFlags;


@RestController
@RequestMapping("/api/v1/feature-flag-manager/")
public class FeatureController {

    @Autowired
    FeatureManager featureManager;


    @GetMapping("/product-listing/enabled")
    public boolean isProductListingEnabled() {
        return featureManager.isActive(FeatureFlags.IS_PRODUCT_LISTING_ENABLED);
    }

}
