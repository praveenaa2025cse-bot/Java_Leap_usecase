package com.example.sewer.controller;

import com.example.sewer.entity.InspectionRoute;
import com.example.sewer.repository.InspectionRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/routes")
public class InspectionRouteController {
    @Autowired
    private InspectionRouteRepository repository;
    @GetMapping
    public List<InspectionRoute> getAll() { return repository.findAll(); }
    @PostMapping
    public InspectionRoute create(@RequestBody InspectionRoute entity) { return repository.save(entity); }
}
