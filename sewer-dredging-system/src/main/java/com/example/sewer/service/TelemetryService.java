package com.example.sewer.service;

import com.example.sewer.entity.*;
import com.example.sewer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TelemetryService {

    @Autowired
    private TelemetryRepository telemetryRepository;
    @Autowired
    private SystemSettingRepository systemSettingRepository;
    @Autowired
    private CrawlerRepository crawlerRepository;
    @Autowired
    private DredgingRunRepository dredgingRunRepository;

    @Transactional
    public Telemetry processTelemetry(Telemetry telemetry) {
        telemetry.setTimestamp(LocalDateTime.now());
        
        // Save telemetry first
        Telemetry savedTelemetry = telemetryRepository.save(telemetry);

        // Check threshold
        SystemSetting thresholdSetting = systemSettingRepository.findBySettingKey("SEDIMENT_THRESHOLD")
                .orElse(null);
        double threshold = 30.0; // Default
        if (thresholdSetting != null && thresholdSetting.getSettingValue() != null) {
            try {
                threshold = Double.parseDouble(thresholdSetting.getSettingValue());
            } catch (NumberFormatException e) {
                // Ignore
            }
        }

        if (savedTelemetry.getSedimentPercentage() != null && savedTelemetry.getSedimentPercentage() > threshold) {
            savedTelemetry.setBlockageDetected(true);
            triggerAutomaticDredging(savedTelemetry, threshold);
        } else {
            savedTelemetry.setBlockageDetected(false);
        }

        return telemetryRepository.save(savedTelemetry);
    }

    private void triggerAutomaticDredging(Telemetry telemetry, double threshold) {
        // Find an available crawler
        Crawler crawlerToAssign = crawlerRepository.findFirstByStatus(Crawler.CrawlerStatus.AVAILABLE)
                .orElse(telemetry.getCrawler()); // Use same crawler if no other available
        
        if (crawlerToAssign != null) {
            crawlerToAssign.setStatus(Crawler.CrawlerStatus.DREDGING);
            crawlerRepository.save(crawlerToAssign);
        }

        DredgingRun run = new DredgingRun();
        run.setRunNumber("DR-" + System.currentTimeMillis());
        run.setCrawler(crawlerToAssign);
        run.setZone(telemetry.getZone());
        run.setTelemetry(telemetry);
        run.setSedimentBeforePercentage(telemetry.getSedimentPercentage());
        run.setPressureThreshold(threshold);
        run.setStatus(DredgingRun.RunStatus.AUTO_TRIGGERED);
        run.setTriggerType(DredgingRun.TriggerType.AUTOMATIC);
        run.setNotes("Auto-triggered due to sediment " + telemetry.getSedimentPercentage() + "% > " + threshold + "%");

        dredgingRunRepository.save(run);
    }
}
