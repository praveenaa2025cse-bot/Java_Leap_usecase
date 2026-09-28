package com.example.sewer.repository;

import com.example.sewer.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
    List<Telemetry> findByCrawlerId(Long crawlerId);
    List<Telemetry> findByZoneId(Long zoneId);
}
