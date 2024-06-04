package com.example.spring_boot_feature_flag_demo.utils;

import com.example.spring_boot_feature_flag_demo.models.Products;

import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;


public class RandomProductGenerator {
    
    private static final String[] DESCRIPTIONS = {
        "high-quality", "state-of-the-art", "reliable", "user-friendly","eco-friendly","versatile"
    };

    private static final String[] ADJECTIVES = {
        "Amazing", "Wonderful", "Innovative", "Perfect", "Stunning", "Revolutionary"
    };

    private static final String[] NOUNS = {
        "Gadget", "Device", "Item", "Product", "Tool", "Accessory"
    };
    private static Random random = new Random();

    public static Products generateRandomProduct() {
        String name = generateRandomName();
        String description = generateRandomDescription();
        BigDecimal price = generateRandomPrice();
        LocalDateTime now = LocalDateTime.now();

        Products product = new Products();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCreatedAt(now);
        product.setUpdatedAt(now);


        return product;
    }

    private static String generateRandomName() {
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        return adjective +" "+ noun;
        
    }

    private static String generateRandomDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("This is a ");
        builder.append(DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);
        builder.append(" product");
        return builder.toString();
    }

    private static BigDecimal generateRandomPrice() {
        double price = 10.0 + (999.0 * random.nextDouble());
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

}

