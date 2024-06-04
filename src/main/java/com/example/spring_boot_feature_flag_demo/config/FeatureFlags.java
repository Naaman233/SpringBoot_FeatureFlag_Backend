package com.example.spring_boot_feature_flag_demo.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum FeatureFlags implements Feature {

    @Label("Price Increase")
    PRICE_INCREASE,

    @Label("New Description")
    DESCRIPTION_UPDATE,

    @Label("20% Discount on all products")
    SAVINGS_DISCOUNT,

    @Label("Product Listing")
    IS_PRODUCT_LISTING_ENABLED
}
