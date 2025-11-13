package com.retailer.insight.service;

import com.retailer.insight.repository.HistoricalDataRepository;
import com.retailer.insight.repository.RealTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AdInsightsServiceImpl implements AdInsightsService{

    private final RealTimeRepository realTimeRepository;
    private final HistoricalDataRepository historicalDataRepository;

    @Override
    public Long getClicks(String tenant, String campaignId, Instant  fromTime, Instant toTime, boolean realtime) {
        if (realtime) {
            return realTimeRepository.getClicks(tenant, campaignId, fromTime, toTime);
        }
        return historicalDataRepository.getClicks(tenant, campaignId, fromTime, toTime);
    }
}

