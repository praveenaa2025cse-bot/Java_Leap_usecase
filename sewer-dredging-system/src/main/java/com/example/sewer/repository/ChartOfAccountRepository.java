package com.example.sewer.repository;

import com.example.sewer.entity.ChartOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccount, Long> {
    Optional<ChartOfAccount> findByAccountName(String accountName);
}
