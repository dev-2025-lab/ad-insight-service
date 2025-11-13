package com.retailer.insight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ClickResponse {
    private String tenantId;
    private String campaignId;
    private long clicks;
    private Instant from;
    private Instant to;
    private String source;
}
