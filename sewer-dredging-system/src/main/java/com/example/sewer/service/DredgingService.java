package com.example.sewer.service;

import com.example.sewer.entity.*;
import com.example.sewer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class DredgingService {

    @Autowired
    private DredgingRunRepository dredgingRunRepository;
    
    @Autowired
    private CrawlerRepository crawlerRepository;

    @Transactional
    public DredgingRun completeDredgingRun(Long runId, Double sludgeRemoved, Double sedimentAfter) {
        DredgingRun run = dredgingRunRepository.findById(runId)
                .orElseThrow(() -> new RuntimeException("Dredging Run not found"));
                
        run.setSludgeRemovedM3(sludgeRemoved);
        run.setSedimentAfterPercentage(sedimentAfter);
        run.setStatus(DredgingRun.RunStatus.COMPLETED);
        run.setEndTime(LocalDateTime.now());
        
        DredgingRun savedRun = dredgingRunRepository.save(run);
        
        // Free the crawler
        Crawler crawler = savedRun.getCrawler();
        if (crawler != null) {
            crawler.setStatus(Crawler.CrawlerStatus.AVAILABLE);
            crawlerRepository.save(crawler);
        }
        
        return savedRun;
    }
}
