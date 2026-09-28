package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    private LocalDate entryDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    private String referenceType; // e.g., CUSTOMER_INVOICE, VENDOR_BILL, PAYMENT
    private Long referenceId;

    private String description;

    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL)
    private List<JournalEntryLine> lines = new ArrayList<>();
}
