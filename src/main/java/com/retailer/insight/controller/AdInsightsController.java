package com.retailer.insight.controller;


import com.retailer.insight.dto.ClickResponse;
import com.retailer.insight.service.AdInsightsService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import static com.retailer.insight.constants.ServiceConstants.*;

/**
 * REST Controller to fetch advertisement click insights.
 *
 * <p>This API provides click metrics for a campaign, supporting both historical and real-time data.
 * The client must provide a valid tenant identifier in the request header.</p>
 **/

@RestController
@RequestMapping("/api/v1/ad")
@RequiredArgsConstructor
public class AdInsightsController {
    private final AdInsightsService service;

    @GetMapping("/{campaignId}/clicks")
    public ResponseEntity<ClickResponse> getClicks(
            @RequestHeader(name = Tenant_ID) @NotBlank(message = "Missing X-Tenant-ID header") String tenantId,
            @PathVariable String campaignId,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant fromTime,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant toTime,
            @RequestParam(defaultValue = "false") Boolean realtime
    ) {
        long clicks = service.getClicks(tenantId, campaignId, fromTime, toTime, realtime);
        return ResponseEntity.ok(new ClickResponse(tenantId, campaignId, clicks, fromTime,  toTime,
                realtime ? REALTIME : HISTORICAL));
    }
}
