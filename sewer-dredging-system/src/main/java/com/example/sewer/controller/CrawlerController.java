package com.example.sewer.controller;

import com.example.sewer.entity.Crawler;
import com.example.sewer.repository.CrawlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/crawlers")
public class CrawlerController {
    @Autowired
    private CrawlerRepository repository;
    @GetMapping
    public List<Crawler> getAll() { return repository.findAll(); }
    @PostMapping
    public Crawler create(@RequestBody Crawler entity) { return repository.save(entity); }
    @GetMapping("/{id}")
    public Crawler getById(@PathVariable Long id) { return repository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public Crawler update(@PathVariable Long id, @RequestBody Crawler entity) { entity.setId(id); return repository.save(entity); }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
}
