package com.example.sewer.controller;

import com.example.sewer.entity.DredgingRun;
import com.example.sewer.service.DredgingService;
import com.example.sewer.repository.DredgingRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sewer/dredging-runs")
public class DredgingRunController {

    @Autowired
    private DredgingRunRepository repository;
    
    @Autowired
    private DredgingService dredgingService;

    @GetMapping
    public List<DredgingRun> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public DredgingRun create(@RequestBody DredgingRun entity) {
        return repository.save(entity);
    }
    
    @PostMapping("/{id}/complete")
    public DredgingRun completeRun(@PathVariable Long id, @RequestBody Map<String, Double> payload) {
        Double sludgeRemoved = payload.get("sludgeRemovedM3");
        Double sedimentAfter = payload.get("sedimentAfterPercentage");
        return dredgingService.completeDredgingRun(id, sludgeRemoved, sedimentAfter);
    }
}
