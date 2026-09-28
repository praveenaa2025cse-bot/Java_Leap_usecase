package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String budgetName;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private SewerZone zone;

    private String financialYear;
    private LocalDate startDate;
    private LocalDate endDate;

    private String category; // e.g. Crawler Maintenance, Power, Dredging Revenue

    private Double allocatedAmount = 0.0;
    private Double actualAmount = 0.0;
    
    public Double getRemainingAmount() {
        return allocatedAmount - actualAmount;
    }
}
