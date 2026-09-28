package com.example.sewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String taxNumber;
    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    public enum ContactType {
        CUSTOMER, VENDOR
    }
}
