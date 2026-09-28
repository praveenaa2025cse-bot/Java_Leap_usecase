package com.example.sewer.repository;

import com.example.sewer.entity.Crawler;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CrawlerRepository extends JpaRepository<Crawler, Long> {
    List<Crawler> findByStatus(Crawler.CrawlerStatus status);
    Optional<Crawler> findFirstByStatus(Crawler.CrawlerStatus status);
}
