package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crawler_id")
    private Crawler crawler;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private SewerZone zone;

    private LocalDateTime timestamp = LocalDateTime.now();
    private Double sedimentPercentage;
    private Double blockagePercentage;
    private Double waterLevel;
    private Double sonarDepth;
    private Double temperature;
    private Double pressure;
    private Double latitude;
    private Double longitude;
    private Boolean blockageDetected;
    
    @Lob
    private String rawData;
}
