package com.retailer.insight.service;

import java.time.Instant;

public interface AdInsightsService {

    Long getClicks(String tenant, String campaignId, Instant  fromTime, Instant toTime, boolean realtime);
}
