package com.example.spring_boot_feature_flag_demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
