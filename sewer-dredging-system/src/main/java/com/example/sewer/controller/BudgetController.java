package com.example.sewer.controller;

import com.example.sewer.entity.Budget;
import com.example.sewer.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/budgets")
public class BudgetController {
    @Autowired
    private BudgetRepository repository;
    @GetMapping
    public List<Budget> getAll() { return repository.findAll(); }
    @PostMapping
    public Budget create(@RequestBody Budget entity) { return repository.save(entity); }
}
