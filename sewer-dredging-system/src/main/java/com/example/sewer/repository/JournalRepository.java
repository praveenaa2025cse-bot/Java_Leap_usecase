package com.example.sewer.repository;

import com.example.sewer.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByType(Journal.JournalType type);
}
