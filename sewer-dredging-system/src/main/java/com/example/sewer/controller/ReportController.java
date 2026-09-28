package com.example.sewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/sewer/reports")
public class ReportController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        Integer totalCrawlers = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM crawler", Integer.class);
        Integer availableCrawlers = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM crawler WHERE status = 'AVAILABLE'", Integer.class);
        Integer dredgingCrawlers = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM crawler WHERE status = 'DREDGING'", Integer.class);
        
        Double totalSludge = jdbcTemplate.queryForObject("SELECT SUM(sludge_removedm3) FROM dredging_run WHERE status = 'COMPLETED'", Double.class);
        
        stats.put("totalCrawlers", totalCrawlers);
        stats.put("availableCrawlers", availableCrawlers);
        stats.put("dredgingCrawlers", dredgingCrawlers);
        stats.put("totalSludge", totalSludge == null ? 0.0 : totalSludge);
        
        return stats;
    }
}
