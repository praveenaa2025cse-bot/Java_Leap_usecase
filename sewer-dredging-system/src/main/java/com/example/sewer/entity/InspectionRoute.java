package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class InspectionRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private SewerZone zone;

    @ManyToOne
    @JoinColumn(name = "crawler_id")
    private Crawler crawler;

    private String startLocation;
    private String endLocation;
    
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    private RouteStatus status;
    
    private String notes;

    public enum RouteStatus {
        PLANNED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
