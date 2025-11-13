package com.retailer.insight.repository;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


/**
 * Mock of Redis
 * Replace with actual RedisTemplate in real implementation.
 */
@Repository
public class RealTimeRepository {


        private static final Map<String, Long> MOCK_REDIS = new HashMap<>();

        static {
            // sample data
            MOCK_REDIS.put("nike:clicks:cmp-001", 120L);
            MOCK_REDIS.put("nike:clicks:cmp-002", 450L);
            MOCK_REDIS.put("adidas:clicks:cmp-001", 310L);
        }

        /**
         * Returns pre-aggregated click count for tenant+campaign.
         * If from/to are provided, a real implementation would sum time-buckets. Leaving it simple for demo
         * purpose
         */
        public long getClicks(String tenantId, String campaignId, Instant  fromTime, Instant toTime) {
            String key = tenantId + ":" + campaignId;
            return MOCK_REDIS.getOrDefault(key, 0L);
        }
}
