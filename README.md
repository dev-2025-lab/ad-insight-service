# Service Overview
This project implements a Spring Boot REST API for retrieving advertisement click insights.  
It provides both real-time and historical campaign analytics such as clicks.

---

# Features
1. Multi-tenant support (tenant isolation via `X-Tenant-ID` header)
2. Real-time insights using Redis
3. Historical insights using ClickHouse
4. Clean layered architecture (Controller → Service → Repository)
5. Centralized error handling (`GlobalExceptionHandler`)

---

# Flow Overview
```
Client → API → AdInsightsController → AdInsightsService
           ├── RealTimeRepository (Redis)
           └── HistoricalDataRepository (ClickHouse)
```

---

# Technology Stack
- Java 21  
- Spring Boot

---

# Setup & Run Instructions
**Build the service**
```bash
mvn clean package
```

**Run locally**
```bash
mvn spring-boot:run
```

---

# API Endpoints
### GET `/ad/{campaignId}/clicks?from=...&to=...&realtime=true`

**Headers:**
```
X-Tenant-ID: nike
```

**Response:**
```json
{
  "tenant": "nike",
  "campaignId": "CAMP-9901",
  "clicks": 12890,
  "realTime": "REALTIME"
}
```
