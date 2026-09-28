package com.example.sewer.controller;

import com.example.sewer.entity.VendorBill;
import com.example.sewer.repository.VendorBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/vendor-bills")
public class VendorBillController {
    @Autowired
    private VendorBillRepository repository;
    @GetMapping
    public List<VendorBill> getAll() { return repository.findAll(); }
    @PostMapping
    public VendorBill create(@RequestBody VendorBill entity) { return repository.save(entity); }
}
