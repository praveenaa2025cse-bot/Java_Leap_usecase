package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    @Enumerated(EnumType.STRING)
    private JournalType type;

    public enum JournalType {
        SALES, PURCHASE, CASH, BANK, GENERAL
    }
}
