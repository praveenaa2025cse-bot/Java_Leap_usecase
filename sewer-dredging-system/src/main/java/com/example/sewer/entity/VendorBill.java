package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class VendorBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billNumber;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Contact vendor;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    private LocalDate billDate;
    private LocalDate dueDate;

    private Double subtotal = 0.0;
    private Double tax = 0.0;
    private Double total = 0.0;

    @Enumerated(EnumType.STRING)
    private BillStatus status;

    public enum BillStatus {
        DRAFT, POSTED, PAID, CANCELLED
    }
}
