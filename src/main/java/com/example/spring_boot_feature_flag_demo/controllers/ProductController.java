package com.example.spring_boot_feature_flag_demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring_boot_feature_flag_demo.models.Products;
import com.example.spring_boot_feature_flag_demo.repository.ProductRepository;
import com.example.spring_boot_feature_flag_demo.config.FeatureFlags;
import com.example.spring_boot_feature_flag_demo.utils.RandomProductGenerator;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FeatureManager featureManager;

    @GetMapping("/foo")
    public List<Products> doFoo() {
        var productListing = new ArrayList<Products>();
        for (int i = 0; i < 10; i++) {
            Products product = RandomProductGenerator.generateRandomProduct();
            productListing.add(product);

        }

        return productRepository.saveAll(productListing);
    }

    
    @GetMapping("/inventory")
    public List<Products> getProducts() {
        List<Products> products = productRepository.findAll();

        if (featureManager.isActive(FeatureFlags.IS_PRODUCT_LISTING_ENABLED)) {
            return List.of();
        }
        if (featureManager.isActive(FeatureFlags.SAVINGS_DISCOUNT)) {
            products.forEach(e -> {
                BigDecimal discountedPrice = e.getPrice().multiply(BigDecimal.valueOf(0.8)).setScale(2, RoundingMode.HALF_UP);
                e.setPrice(discountedPrice);
            });
        }

        if (featureManager.isActive(FeatureFlags.PRICE_INCREASE)) {
            products.forEach(e -> {
                BigDecimal increasedPrice = e.getPrice().multiply(BigDecimal.valueOf(1.2)).setScale(2, RoundingMode.HALF_UP);
                e.setPrice(increasedPrice);
            });
        }
        return products;
    }
}
