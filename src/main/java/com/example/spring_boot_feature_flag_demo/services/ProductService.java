package com.example.spring_boot_feature_flag_demo.services;

import java.util.List;

import com.example.spring_boot_feature_flag_demo.models.Products;

public interface ProductService {

    List<Products> getProducts();

    Products getProductById(Long id);

    Products createProduct (Products createdProduct);

    Products applyPriceIncrease(Products product);

    Products apply20PercentDiscount(Products product);
}
