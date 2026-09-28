-- Sample Data initialization SQL script.
-- Note: The application uses a DataSeeder Java component to automatically populate data on startup.
-- This file is provided for manual execution if required by external tooling.

INSERT INTO system_setting (setting_key, setting_value) VALUES ('SEDIMENT_THRESHOLD', '30');

INSERT INTO contact (name, contact_type) VALUES ('Coimbatore Municipal Sanitation District', 'CUSTOMER');
INSERT INTO contact (name, contact_type) VALUES ('Robotic Dredging Hardware Vendor', 'VENDOR');

INSERT INTO sewer_zone (zone_code, zone_name) VALUES ('Z-001', 'Main Trunk Sector 5');

INSERT INTO crawler (crawler_code, crawler_name, status, battery_percentage) VALUES ('CRAWLER-001', 'DredgeBot Alpha', 'AVAILABLE', 100);
INSERT INTO crawler (crawler_code, crawler_name, status, battery_percentage) VALUES ('CRAWLER-002', 'DredgeBot Beta', 'AVAILABLE', 95);

INSERT INTO product (name, type, unit, unit_price) VALUES ('Sub-Surface Sludge Dredging m³', 'SERVICE', 'm³', 500);
INSERT INTO product (name, type, stock_quantity) VALUES ('High-Pressure Nozzle Assembly', 'GOODS', 10);
