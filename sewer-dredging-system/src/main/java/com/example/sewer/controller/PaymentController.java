package com.example.sewer.controller;

import com.example.sewer.entity.Payment;
import com.example.sewer.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository repository;
    @GetMapping
    public List<Payment> getAll() { return repository.findAll(); }
    @PostMapping
    public Payment create(@RequestBody Payment entity) { return repository.save(entity); }
}
