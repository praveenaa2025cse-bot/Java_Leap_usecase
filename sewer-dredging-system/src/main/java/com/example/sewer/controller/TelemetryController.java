package com.example.sewer.controller;

import com.example.sewer.entity.Telemetry;
import com.example.sewer.service.TelemetryService;
import com.example.sewer.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryRepository repository;
    
    @Autowired
    private TelemetryService telemetryService;

    @GetMapping
    public List<Telemetry> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Telemetry create(@RequestBody Telemetry telemetry) {
        return telemetryService.processTelemetry(telemetry);
    }
    
    @GetMapping("/crawler/{crawlerId}")
    public List<Telemetry> getByCrawlerId(@PathVariable Long crawlerId) {
        return repository.findByCrawlerId(crawlerId);
    }
    
    @GetMapping("/zone/{zoneId}")
    public List<Telemetry> getByZoneId(@PathVariable Long zoneId) {
        return repository.findByZoneId(zoneId);
    }
}
