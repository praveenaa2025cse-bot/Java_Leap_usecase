package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private String description;
    private String unit;
    private Double unitPrice;
    private Double costPrice;
    private Integer stockQuantity = 0;
    private Integer reorderLevel;
    private boolean active = true;

    public enum ProductType {
        SERVICE, GOODS
    }
}
