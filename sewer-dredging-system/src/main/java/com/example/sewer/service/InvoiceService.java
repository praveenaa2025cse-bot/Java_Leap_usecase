package com.example.sewer.service;

import com.example.sewer.entity.*;
import com.example.sewer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class InvoiceService {

    @Autowired
    private CustomerInvoiceRepository invoiceRepository;
    
    @Autowired
    private DredgingRunRepository dredgingRunRepository;

    @Autowired
    private AccountingService accountingService;
    
    @Autowired
    private JournalRepository journalRepository;
    
    @Autowired
    private ChartOfAccountRepository accountRepository;

    @Transactional
    public CustomerInvoice generateInvoiceFromDredging(Long dredgingRunId, Long customerId, Double ratePerM3) {
        DredgingRun run = dredgingRunRepository.findById(dredgingRunId)
                .orElseThrow(() -> new RuntimeException("Dredging Run not found"));
                
        if (run.getStatus() != DredgingRun.RunStatus.COMPLETED) {
            throw new RuntimeException("Cannot invoice an incomplete dredging run.");
        }
        
        // Prevent duplicate invoicing
        if (invoiceRepository.findByDredgingRunId(run.getId()).isPresent()) {
            throw new RuntimeException("Invoice already exists for this dredging run.");
        }

        Double sludgeRemoved = run.getSludgeRemovedM3();
        if (sludgeRemoved == null || sludgeRemoved <= 0) {
            throw new RuntimeException("No sludge removed to invoice.");
        }

        Double totalAmount = sludgeRemoved * ratePerM3;

        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        // set customer proxy based on ID
        Contact customer = new Contact();
        customer.setId(customerId);
        invoice.setCustomer(customer);
        invoice.setDredgingRun(run);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setDueDate(LocalDate.now().plusDays(30));
        invoice.setSubtotal(totalAmount);
        invoice.setTotal(totalAmount);
        invoice.setStatus(CustomerInvoice.InvoiceStatus.POSTED); // directly post for simplicity

        CustomerInvoice savedInvoice = invoiceRepository.save(invoice);
        
        // Generate Accounting Entry
        postInvoiceAccountingEntry(savedInvoice);

        return savedInvoice;
    }

    private void postInvoiceAccountingEntry(CustomerInvoice invoice) {
        Journal salesJournal = journalRepository.findByType(Journal.JournalType.SALES).orElse(null);
        ChartOfAccount arAccount = accountRepository.findByAccountName("Customer Receivable").orElse(null);
        ChartOfAccount revenueAccount = accountRepository.findByAccountName("Sludge Removal Revenue").orElse(null);
        
        if (salesJournal == null || arAccount == null || revenueAccount == null) {
            // Log warning: Accounting setup incomplete. Skip entry for now.
            return;
        }

        JournalEntry entry = new JournalEntry();
        entry.setJournal(salesJournal);
        entry.setEntryDate(invoice.getInvoiceDate());
        entry.setReferenceType("CUSTOMER_INVOICE");
        entry.setReferenceId(invoice.getId());
        entry.setDescription("Invoice " + invoice.getInvoiceNumber());

        JournalEntryLine debit = new JournalEntryLine();
        debit.setAccount(arAccount);
        debit.setDebit(invoice.getTotal());
        debit.setDescription("Accounts Receivable");
        entry.getLines().add(debit);

        JournalEntryLine credit = new JournalEntryLine();
        credit.setAccount(revenueAccount);
        credit.setCredit(invoice.getTotal());
        credit.setDescription("Revenue");
        entry.getLines().add(credit);

        accountingService.postJournalEntry(entry);
    }
}
