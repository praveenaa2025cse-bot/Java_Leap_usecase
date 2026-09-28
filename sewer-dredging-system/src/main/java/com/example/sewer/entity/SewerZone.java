package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SewerZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneCode;
    private String zoneName;
    private String description;
    private String sewerType;
    private String location;
    private Double lengthKm;
    private Double budgetAmount;
    private boolean active = true;
}
