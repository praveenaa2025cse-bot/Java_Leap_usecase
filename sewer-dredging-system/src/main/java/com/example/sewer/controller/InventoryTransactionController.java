package com.example.sewer.controller;

import com.example.sewer.entity.InventoryTransaction;
import com.example.sewer.repository.InventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/inventory")
public class InventoryTransactionController {
    @Autowired
    private InventoryTransactionRepository repository;
    @GetMapping
    public List<InventoryTransaction> getAll() { return repository.findAll(); }
    @PostMapping
    public InventoryTransaction create(@RequestBody InventoryTransaction entity) { return repository.save(entity); }
}
