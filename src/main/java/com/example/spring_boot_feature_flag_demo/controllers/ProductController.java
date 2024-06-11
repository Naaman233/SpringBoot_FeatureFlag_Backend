package com.example.spring_boot_feature_flag_demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.spring_boot_feature_flag_demo.models.Products;

import com.example.spring_boot_feature_flag_demo.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service; 

    
    @GetMapping("/inventory")
    private ResponseEntity<?> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProducts());
    }

    @GetMapping("/getProductById")
    private ResponseEntity<?> getProductById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
    }

    @PostMapping("/createProduct")
    private ResponseEntity<?> createProduct(@RequestBody Products products) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(products));
    }




}
