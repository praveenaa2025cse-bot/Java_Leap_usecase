package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChartOfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountCode;
    private String accountName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "parent_account_id")
    private ChartOfAccount parentAccount;

    private Double openingBalance = 0.0;
    private boolean active = true;

    public enum AccountType {
        ASSET, LIABILITY, INCOME, EXPENSE, EQUITY
    }
}
