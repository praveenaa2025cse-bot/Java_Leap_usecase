package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class DredgingRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String runNumber;

    @ManyToOne
    @JoinColumn(name = "crawler_id")
    private Crawler crawler;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private SewerZone zone;

    @ManyToOne
    @JoinColumn(name = "telemetry_id")
    private Telemetry telemetry;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private Double sedimentBeforePercentage;
    private Double sedimentAfterPercentage;
    
    private Double sludgeRemovedM3;
    
    private Double dredgingPressure;
    private Double pressureThreshold;
    
    @Enumerated(EnumType.STRING)
    private RunStatus status;
    
    @Enumerated(EnumType.STRING)
    private TriggerType triggerType;
    
    private String notes;

    public enum RunStatus {
        AUTO_TRIGGERED, SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }

    public enum TriggerType {
        AUTOMATIC, MANUAL
    }
}
