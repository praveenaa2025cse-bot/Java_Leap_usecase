package com.example.sewer.controller;

import com.example.sewer.entity.CustomerInvoice;
import com.example.sewer.service.InvoiceService;
import com.example.sewer.repository.CustomerInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/invoices")
public class CustomerInvoiceController {

    @Autowired
    private CustomerInvoiceRepository repository;
    
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<CustomerInvoice> getAll() {
        return repository.findAll();
    }

    @PostMapping("/generate")
    public CustomerInvoice generateInvoice(@RequestParam Long dredgingRunId, @RequestParam Long customerId, @RequestParam Double ratePerM3) {
        return invoiceService.generateInvoiceFromDredging(dredgingRunId, customerId, ratePerM3);
    }
}
