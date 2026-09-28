# Autonomous Sub-Surface Sewer Inspection & Robotic Sludge Dredging Grid

## 1. Project Overview
The Autonomous Sub-Surface Sewer Inspection & Robotic Sludge Dredging Grid is an urban sanitation management and accounting platform for municipal sewer infrastructure. It manages autonomous submersible sewer crawlers, monitoring telemetry data to automatically dispatch dredging runs when sediment accumulation exceeds configured thresholds.

## 2. Features
- **Crawler & Infrastructure Management**: Track active robotic crawlers, sewer zones, and inspection routes.
- **Automated Dredging Triggers**: Automatically starts dredging runs when sediment percentage exceeds 30%.
- **Financial & Operations Accounting**: Full double-entry accounting ledger to manage Vendor Bills, Customer Invoices, and Budgets.
- **Inventory Management**: Purchase Order and stock tracking for High-Pressure Nozzle Assemblies.
- **Dashboards & Reporting**: Real-time operational dashboard, Balance Sheet, Profit & Loss, and Budget Variance reports.

## 3. Technologies
- **Backend**: Java 17, Spring Boot 3.x, Spring Web, Spring Data JPA, Hibernate, Spring Security.
- **Database**: MySQL 8.x
- **Frontend**: HTML5, CSS3, Vanilla JavaScript, Fetch API.

## 4. Requirements
- JDK 17 or higher
- Maven 3.6+
- MySQL 8.x

## 5. MySQL Installation & Configuration
Ensure you have MySQL Server installed and running on port 3306.

## 6. Database Creation
The application uses the `createDatabaseIfNotExist=true` flag in its JDBC URL, so the database will be created automatically. However, you can create it manually if needed:
```sql
CREATE DATABASE sewer_dredging_db;
```

## 7. MySQL Username/Password Configuration
Open `src/main/resources/application.properties`.
Find the following lines and update them with your actual MySQL credentials:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

## 8. How to run Spring Boot
Navigate to the project root directory (`sewer-dredging-system`) in your terminal and run:
```bash
mvn spring-boot:run
```
Alternatively, build the project and run the JAR:
```bash
mvn clean install
java -jar target/sewer-dredging-system-0.0.1-SNAPSHOT.jar
```

## 9. How to access frontend
Once the application is running, open your web browser and go to:
http://localhost:8080/dashboard.html (or http://localhost:8080/login.html if redirected)

## 10. Default login credentials
The application comes with default users seeded on startup:
**Admin (Sanitation Operations Director)**
- Username: `admin`
- Password: `admin123`

**Accountant (Utility Accountant)**
- Username: `accountant`
- Password: `accountant123`

## 11. REST API endpoints
The backend exposes multiple REST APIs:
- `POST /api/sewer/crawlers`
- `GET /api/sewer/telemetry`
- `POST /api/sewer/telemetry`
- `POST /api/sewer/dredging/trigger`
- ...and many others.

## 12. Example API requests
**Example telemetry request:**
```bash
curl -X POST http://localhost:8080/api/sewer/telemetry \
     -H "Content-Type: application/json" \
     -d '{
           "crawlerId": 1,
           "zoneId": 1,
           "sedimentPercentage": 35,
           "blockagePercentage": 40,
           "waterLevel": 2.5,
           "sonarDepth": 5.2,
           "temperature": 28.4,
           "pressure": 4.5,
           "latitude": 11.0168,
           "longitude": 76.9558
         }'
```

## 13. Example automatic dredging workflow
When the telemetry POST above is sent, the system will compare the `sedimentPercentage` (35) against the default threshold (30). Because 35 > 30, it will automatically create a Dredging Run in the database and assign the crawler to `DREDGING` status.

## 14. Example invoice workflow
1. Operator navigates to the Dredging Run that was just created.
2. Operator completes the run, inputting the volume of sludge removed (e.g., 125 m³).
3. The Accountant can now navigate to Invoices and generate an invoice based on the completed Dredging Run.
4. An invoice is created with a total of ₹62,500 (125 m³ × ₹500/m³).

## 15. Example accounting entries
When an invoice is posted:
- Debit: Customer Receivable (₹62,500)
- Credit: Sludge Removal Revenue (₹62,500)
When the customer payment is recorded:
- Debit: Cash/Bank (₹62,500)
- Credit: Customer Receivable (₹62,500)
