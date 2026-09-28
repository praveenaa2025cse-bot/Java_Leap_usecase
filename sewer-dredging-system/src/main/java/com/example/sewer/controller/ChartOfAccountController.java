package com.example.sewer.controller;

import com.example.sewer.entity.ChartOfAccount;
import com.example.sewer.repository.ChartOfAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/accounts")
public class ChartOfAccountController {
    @Autowired
    private ChartOfAccountRepository repository;
    @GetMapping
    public List<ChartOfAccount> getAll() { return repository.findAll(); }
    @PostMapping
    public ChartOfAccount create(@RequestBody ChartOfAccount entity) { return repository.save(entity); }
}
