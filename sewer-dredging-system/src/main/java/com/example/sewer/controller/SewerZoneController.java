package com.example.sewer.controller;

import com.example.sewer.entity.SewerZone;
import com.example.sewer.repository.SewerZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/zones")
public class SewerZoneController {
    @Autowired
    private SewerZoneRepository repository;
    @GetMapping
    public List<SewerZone> getAll() { return repository.findAll(); }
    @PostMapping
    public SewerZone create(@RequestBody SewerZone entity) { return repository.save(entity); }
    @GetMapping("/{id}")
    public SewerZone getById(@PathVariable Long id) { return repository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public SewerZone update(@PathVariable Long id, @RequestBody SewerZone entity) { entity.setId(id); return repository.save(entity); }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
}
