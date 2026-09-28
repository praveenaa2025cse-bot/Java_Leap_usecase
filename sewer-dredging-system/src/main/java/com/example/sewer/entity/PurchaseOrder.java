package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNumber;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Contact vendor;

    private LocalDateTime orderDate;
    private LocalDateTime expectedDate;

    @Enumerated(EnumType.STRING)
    private PoStatus status;

    private Double subtotal = 0.0;
    private Double tax = 0.0;
    private Double total = 0.0;
    private String notes;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderLine> lines = new ArrayList<>();

    public enum PoStatus {
        DRAFT, APPROVED, RECEIVED, CANCELLED
    }
}
