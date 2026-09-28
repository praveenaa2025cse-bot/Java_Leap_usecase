package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Crawler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crawlerCode;
    private String crawlerName;
    private String model;
    private String manufacturer;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private CrawlerStatus status;

    private Integer batteryPercentage;
    private String currentLocation;

    @ManyToOne
    @JoinColumn(name = "assigned_zone_id")
    private SewerZone assignedZone;

    private LocalDateTime lastInspectionDate;
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum CrawlerStatus {
        AVAILABLE, INSPECTION, DREDGING, MAINTENANCE, CHARGING, OFFLINE
    }
}
