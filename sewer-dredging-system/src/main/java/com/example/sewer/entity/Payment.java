package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentNumber;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "vendor_bill_id")
    private VendorBill vendorBill;

    @ManyToOne
    @JoinColumn(name = "customer_invoice_id")
    private CustomerInvoice customerInvoice;

    private LocalDate paymentDate;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String bankReference;
    private String notes;

    public enum PaymentType {
        CUSTOMER_RECEIPT, VENDOR_PAYMENT
    }

    public enum PaymentMethod {
        BANK_TRANSFER, CASH
    }
}
