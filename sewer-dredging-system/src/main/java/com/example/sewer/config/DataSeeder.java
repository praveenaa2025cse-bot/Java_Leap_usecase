package com.example.sewer.config;

import com.example.sewer.entity.*;
import com.example.sewer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private ContactRepository contactRepo;
    @Autowired private SewerZoneRepository zoneRepo;
    @Autowired private CrawlerRepository crawlerRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private ChartOfAccountRepository accountRepo;
    @Autowired private JournalRepository journalRepo;
    @Autowired private SystemSettingRepository settingRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // System Settings
        if (settingRepo.count() == 0) {
            SystemSetting threshold = new SystemSetting();
            threshold.setSettingKey("SEDIMENT_THRESHOLD");
            threshold.setSettingValue("30");
            settingRepo.save(threshold);
        }

        // Contacts
        if (contactRepo.count() == 0) {
            Contact customer = new Contact();
            customer.setName("Coimbatore Municipal Sanitation District");
            customer.setContactType(Contact.ContactType.CUSTOMER);
            contactRepo.save(customer);

            Contact vendor = new Contact();
            vendor.setName("Robotic Dredging Hardware Vendor");
            vendor.setContactType(Contact.ContactType.VENDOR);
            contactRepo.save(vendor);
        }

        // Zone
        if (zoneRepo.count() == 0) {
            SewerZone zone = new SewerZone();
            zone.setZoneCode("Z-001");
            zone.setZoneName("Main Trunk Sector 5");
            zone.setLengthKm(15.5);
            zoneRepo.save(zone);
        }

        // Crawlers
        if (crawlerRepo.count() == 0) {
            Crawler c1 = new Crawler();
            c1.setCrawlerCode("CRAWLER-001");
            c1.setCrawlerName("DredgeBot Alpha");
            c1.setStatus(Crawler.CrawlerStatus.AVAILABLE);
            c1.setBatteryPercentage(100);
            crawlerRepo.save(c1);

            Crawler c2 = new Crawler();
            c2.setCrawlerCode("CRAWLER-002");
            c2.setCrawlerName("DredgeBot Beta");
            c2.setStatus(Crawler.CrawlerStatus.AVAILABLE);
            c2.setBatteryPercentage(95);
            crawlerRepo.save(c2);
        }

        // Products
        if (productRepo.count() == 0) {
            Product p1 = new Product();
            p1.setName("Sub-Surface Sludge Dredging m³");
            p1.setType(Product.ProductType.SERVICE);
            p1.setUnit("m³");
            p1.setUnitPrice(500.0);
            productRepo.save(p1);

            Product p2 = new Product();
            p2.setName("High-Pressure Nozzle Assembly");
            p2.setType(Product.ProductType.GOODS);
            p2.setStockQuantity(10);
            productRepo.save(p2);
        }

        // Journals & Accounts
        if (journalRepo.count() == 0) {
            Journal salesJournal = new Journal();
            salesJournal.setCode("SALES");
            salesJournal.setName("Sales Journal");
            salesJournal.setType(Journal.JournalType.SALES);
            journalRepo.save(salesJournal);
        }

        if (accountRepo.count() == 0) {
            ChartOfAccount ar = new ChartOfAccount();
            ar.setAccountName("Customer Receivable");
            ar.setAccountType(ChartOfAccount.AccountType.ASSET);
            accountRepo.save(ar);

            ChartOfAccount rev = new ChartOfAccount();
            rev.setAccountName("Sludge Removal Revenue");
            rev.setAccountType(ChartOfAccount.AccountType.INCOME);
            accountRepo.save(rev);
            
            ChartOfAccount cash = new ChartOfAccount();
            cash.setAccountName("Cash/Bank");
            cash.setAccountType(ChartOfAccount.AccountType.ASSET);
            accountRepo.save(cash);
        }
    }
}
