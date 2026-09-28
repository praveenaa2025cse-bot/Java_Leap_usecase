package com.example.sewer.controller;

import com.example.sewer.entity.SalesOrder;
import com.example.sewer.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/sales-orders")
public class SalesOrderController {
    @Autowired
    private SalesOrderRepository repository;
    @GetMapping
    public List<SalesOrder> getAll() { return repository.findAll(); }
    @PostMapping
    public SalesOrder create(@RequestBody SalesOrder entity) { return repository.save(entity); }
}
