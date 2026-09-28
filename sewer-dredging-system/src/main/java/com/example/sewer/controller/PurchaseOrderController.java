package com.example.sewer.controller;

import com.example.sewer.entity.PurchaseOrder;
import com.example.sewer.service.PurchaseService;
import com.example.sewer.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderRepository repository;
    
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder entity) {
        if(entity.getLines() != null) {
            entity.getLines().forEach(line -> line.setPurchaseOrder(entity));
        }
        return repository.save(entity);
    }
    
    @PostMapping("/{id}/receive")
    public PurchaseOrder receive(@PathVariable Long id) {
        return purchaseService.receivePurchaseOrder(id);
    }
}
