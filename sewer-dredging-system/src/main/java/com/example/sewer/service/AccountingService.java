package com.example.sewer.service;

import com.example.sewer.entity.JournalEntry;
import com.example.sewer.entity.JournalEntryLine;
import com.example.sewer.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountingService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Transactional
    public JournalEntry postJournalEntry(JournalEntry entry) {
        double totalDebit = 0.0;
        double totalCredit = 0.0;

        for (JournalEntryLine line : entry.getLines()) {
            totalDebit += (line.getDebit() != null ? line.getDebit() : 0.0);
            totalCredit += (line.getCredit() != null ? line.getCredit() : 0.0);
            line.setJournalEntry(entry);
        }

        // Must balance to 2 decimal places to avoid floating point issues
        if (Math.abs(totalDebit - totalCredit) > 0.01) {
            throw new RuntimeException("Unbalanced journal entry: Debit=" + totalDebit + " Credit=" + totalCredit);
        }

        return journalEntryRepository.save(entry);
    }
}
