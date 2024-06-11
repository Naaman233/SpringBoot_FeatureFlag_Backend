package com.example.spring_boot_feature_flag_demo.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.spring_boot_feature_flag_demo.repository.ProductRepository;
import com.example.spring_boot_feature_flag_demo.models.Products;
import com.example.spring_boot_feature_flag_demo.utils.RandomProductGenerator;


import java.util.ArrayList;
@Component
@Configuration
public class ProductInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public ApplicationRunner initializeProduct () {
        return args -> {
            var productListing = new ArrayList<Products>();
            for (int i = 0; i <= 10; i++) {
                Products product = RandomProductGenerator.generateRandomProduct();
                productListing.add(product);
            }
            productRepository.saveAll(productListing);
        };
    }

}
