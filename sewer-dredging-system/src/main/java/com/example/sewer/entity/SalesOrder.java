package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Contact customer;

    private LocalDate orderDate;
    private String servicePeriod;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private SewerZone zone;

    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;

    private Double subtotal = 0.0;
    private Double tax = 0.0;
    private Double total = 0.0;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    private List<SalesOrderLine> lines = new ArrayList<>();

    public enum SalesOrderStatus {
        DRAFT, APPROVED, INVOICED, CANCELLED
    }
}
