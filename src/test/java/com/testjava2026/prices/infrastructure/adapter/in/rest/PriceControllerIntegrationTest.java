package com.testjava2026.prices.infrastructure.adapter.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test1_precioTarifa1_el14JunioA10h() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T10:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.priceList").value(1))
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test2_precioTarifa2_el14JunioA16h() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T16:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(2))
				.andExpect(jsonPath("$.price").value(25.45));
	}

	@Test
	void test3_precioTarifa1_el14JunioA21h() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T21:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(1))
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test4_precioTarifa3_el15JunioA10h() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-15T10:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(3))
				.andExpect(jsonPath("$.price").value(30.50));
	}

	@Test
	void test5_precioTarifa4_el16JunioA21h() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-16T21:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(4))
				.andExpect(jsonPath("$.price").value(38.95));
	}
}
