package com.example.spring_boot_feature_flag_demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_feature_flag_demo.models.Products;

public interface ProductRepository extends JpaRepository<Products,Long>{

}
