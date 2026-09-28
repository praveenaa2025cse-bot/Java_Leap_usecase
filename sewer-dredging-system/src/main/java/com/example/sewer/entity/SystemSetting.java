package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SystemSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String settingKey;
    private String settingValue;
    private String description;
}
