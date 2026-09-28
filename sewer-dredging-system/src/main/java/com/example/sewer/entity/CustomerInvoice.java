package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CustomerInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Contact customer;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    @OneToOne
    @JoinColumn(name = "dredging_run_id")
    private DredgingRun dredgingRun;

    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private Double subtotal = 0.0;
    private Double tax = 0.0;
    private Double total = 0.0;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public enum InvoiceStatus {
        DRAFT, POSTED, PAID, CANCELLED
    }
}
