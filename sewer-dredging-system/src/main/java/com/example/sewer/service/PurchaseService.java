package com.example.sewer.service;

import com.example.sewer.entity.*;
import com.example.sewer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseOrderRepository poRepository;
    
    @Autowired
    private InventoryTransactionRepository inventoryRepo;
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public PurchaseOrder receivePurchaseOrder(Long poId) {
        PurchaseOrder po = poRepository.findById(poId)
            .orElseThrow(() -> new RuntimeException("PO not found"));
            
        if (po.getStatus() == PurchaseOrder.PoStatus.RECEIVED) {
            throw new RuntimeException("PO already received");
        }
        
        for (PurchaseOrderLine line : po.getLines()) {
            // Update stock
            Product p = line.getProduct();
            if (p.getType() == Product.ProductType.GOODS) {
                p.setStockQuantity(p.getStockQuantity() + line.getQuantity());
                productRepository.save(p);
                
                // Record transaction
                InventoryTransaction txn = new InventoryTransaction();
                txn.setProduct(p);
                txn.setTransactionType(InventoryTransaction.TransactionType.PURCHASE);
                txn.setQuantity(line.getQuantity());
                txn.setReferenceType("PURCHASE_ORDER");
                txn.setReferenceId(po.getId());
                inventoryRepo.save(txn);
            }
        }
        
        po.setStatus(PurchaseOrder.PoStatus.RECEIVED);
        return poRepository.save(po);
    }
}
