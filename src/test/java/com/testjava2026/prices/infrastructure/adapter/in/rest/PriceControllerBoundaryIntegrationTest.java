package com.testjava2026.prices.infrastructure.adapter.in.rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerBoundaryIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@ParameterizedTest(name = "{0} -> tarifa {1}")
	@CsvSource({
			"2020-06-14T14:59:59, 1",
			"2020-06-14T15:00:00, 2",
			"2020-06-14T18:30:00, 2",
			"2020-06-14T18:30:01, 1",
			"2020-06-15T00:00:00, 3",
			"2020-06-15T11:00:00, 3",
			"2020-06-15T11:00:01, 1",
			"2020-06-15T15:59:59, 1",
			"2020-06-15T16:00:00, 4",
			"2020-12-31T23:59:59, 4"
	})
	void aplicaLaTarifaCorrectaEnLosLimites(String applicationDate, int expectedPriceList) throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", applicationDate)
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(expectedPriceList));
	}
}
