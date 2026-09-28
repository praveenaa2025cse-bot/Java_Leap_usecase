package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Integer quantity;
    
    private String referenceType; // e.g. PURCHASE_ORDER
    private Long referenceId; // e.g. PO ID

    private LocalDateTime transactionDate = LocalDateTime.now();

    public enum TransactionType {
        PURCHASE, SALE, CONSUMPTION, ADJUSTMENT, RETURN
    }
}
