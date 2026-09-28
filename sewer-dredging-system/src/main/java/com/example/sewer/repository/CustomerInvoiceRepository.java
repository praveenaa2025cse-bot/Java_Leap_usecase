package com.example.sewer.repository;

import com.example.sewer.entity.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Long> {
    Optional<CustomerInvoice> findByDredgingRunId(Long dredgingRunId);
}
