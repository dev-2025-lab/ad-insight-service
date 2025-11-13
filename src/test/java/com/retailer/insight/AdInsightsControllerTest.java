package com.retailer.insight;

import com.retailer.insight.controller.AdInsightsController;
import com.retailer.insight.service.AdInsightsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdInsightsController.class)
class AdInsightsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean // ✅ Replace @MockBean
	private AdInsightsService service;

	private static final String TENANT_ID = "X-Tenant-ID";

	@Test
	void getClicksSuccessfully() throws Exception {
		String tenant = "nike";
		String campaignId = "cmp123";
		Instant startTime = Instant.parse("2025-01-01T00:00:00Z");
		Instant toTime = Instant.parse("2025-01-01T00:00:00Z");

		when(service.getClicks(tenant, campaignId, startTime, toTime, true))
				.thenReturn(150L);

		mockMvc.perform(
						get("/api/v1/ad/{campaignId}/clicks", campaignId)
								.header(TENANT_ID, tenant)
								.param("fromTime", startTime.toString())
								.param("toTime", toTime.toString())
								.param("realtime", "true")
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tenantId").value(tenant))
				.andExpect(jsonPath("$.campaignId").value(campaignId))
				.andExpect(jsonPath("$.clicks").value(150))
				.andExpect(jsonPath("$.source").value("REALTIME"));
	}

	@Test
	void return400_WhenTenantHeaderMissing() throws Exception {
		String campaignId = "cmp123";

		mockMvc.perform(get("/api/v1/ad/{campaignId}/clicks", campaignId)
						//TENANT_ID header omitted
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
