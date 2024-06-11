package com.example.spring_boot_feature_flag_demo.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

import lombok.RequiredArgsConstructor;

import com.example.spring_boot_feature_flag_demo.models.Products;
import com.example.spring_boot_feature_flag_demo.repository.ProductRepository;
import com.example.spring_boot_feature_flag_demo.config.FeatureFlags;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    private final FeatureManager featureManager;

   
    @Override
    public List<Products> getProducts() {
        List<Products> listOfProducts = repository.findAll();
        if (featureManager.isActive(FeatureFlags.PRICE_INCREASE)) {
            return listOfProducts
                .stream()
                .map(this::applyPriceIncrease)
                .collect(Collectors.toList());
        }

        if (featureManager.isActive(FeatureFlags.SAVINGS_DISCOUNT)) {
            return listOfProducts
                    .stream()
                    .map(this::apply20PercentDiscount)
                    .collect(Collectors.toList());
        }

        return listOfProducts;
        
    }

    @Override
    public Products getProductById(Long id) {
        Optional<Products> product = repository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public Products createProduct(Products createdProduct) {
        Products productSaved = repository.save(Products.builder()
        .name(createdProduct.getName())
        .description(createdProduct.getDescription())
        .price(createdProduct.getPrice())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build());
        
        return productSaved;
    }

    @Override
    public Products applyPriceIncrease(Products product) {
        Optional.ofNullable(product.getPrice())
        .map(price -> price.multiply(BigDecimal.valueOf(1.2))
        .setScale(2, RoundingMode.HALF_UP))
        .ifPresent(product::setPrice);
        return product; 
    }

    @Override
    public Products apply20PercentDiscount(Products product) {
        Optional.ofNullable(product.getPrice())
        .map(price -> price.multiply(BigDecimal.valueOf(0.8))
        .setScale(2, RoundingMode.HALF_UP))
        .ifPresent(product::setPrice);
        return product;
    }

}
