package com.example.sewer.controller;

import com.example.sewer.entity.JournalEntry;
import com.example.sewer.service.AccountingService;
import com.example.sewer.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/journal-entries")
public class JournalEntryController {
    @Autowired
    private JournalEntryRepository repository;
    @Autowired
    private AccountingService accountingService;
    
    @GetMapping
    public List<JournalEntry> getAll() { return repository.findAll(); }
    
    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entity) { 
        return accountingService.postJournalEntry(entity);
    }
}
