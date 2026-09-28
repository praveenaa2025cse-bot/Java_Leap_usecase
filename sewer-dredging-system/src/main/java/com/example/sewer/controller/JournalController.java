package com.example.sewer.controller;

import com.example.sewer.entity.Journal;
import com.example.sewer.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/journals")
public class JournalController {
    @Autowired
    private JournalRepository repository;
    @GetMapping
    public List<Journal> getAll() { return repository.findAll(); }
    @PostMapping
    public Journal create(@RequestBody Journal entity) { return repository.save(entity); }
}
