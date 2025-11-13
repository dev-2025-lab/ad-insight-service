package com.retailer.insight.repository;


import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock of ClickHouse / OLAP storage.
 * In real system, we would use ClickHouse client with parameterized queries.
 */
@Repository
public class HistoricalDataRepository {

    private static final Map<String, Long> MOCK_CLICKHOUSE = new HashMap<>();

    static {
        // keyed by: "<tenant>:<campaign>:YYYY-MM" for demo aggregated values
        MOCK_CLICKHOUSE.put("nike:cmp-001:2025-01", 15500L);
        MOCK_CLICKHOUSE.put("nike:cmp-002:2025-01", 72000L);
        MOCK_CLICKHOUSE.put("adidas:cmp-001:2025-01", 51000L);
    }

    /**
     * For demo, we convert requested 'from' to a YYYY-MM month and return a value.
     * In real implementation use SQL:
     *   SELECT SUM(count) FROM ad_events WHERE tenant_id=? AND campaign_id=? AND ts BETWEEN from AND to
     */
    public long getClicks(String tenantId, String campaignId, Instant  fromTime, Instant toTime) {
        String month = (fromTime != null) ? fromTime.toString().substring(0, 7) : "2025-01";
        String key = tenantId + ":" + campaignId + ":" + month;
        return MOCK_CLICKHOUSE.getOrDefault(key, 0L);
    }
}
