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
class PriceControllerErrorIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void devuelve404CuandoNoHayTarifaParaLaFecha() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2019-01-01T00:00:00")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.message").value("No se encontró tarifa aplicable"));
	}

	@Test
	void devuelve404CuandoElProductoNoExiste() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T10:00:00")
						.param("brandId", "1")
						.param("productId", "99999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.message").value("No se encontró tarifa aplicable"));
	}

	@Test
	void devuelve400CuandoLaFechaTieneFormatoInvalido() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "no-es-una-fecha")
						.param("brandId", "1")
						.param("productId", "35455"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.message").value("Parámetro applicationDate inválido"));
	}

	@Test
	void devuelve400CuandoBrandIdNoEsNumerico() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T10:00:00")
						.param("brandId", "abc")
						.param("productId", "35455"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.message").value("Parámetro inválido"));
	}

	@Test
	void devuelve400CuandoFaltaElProductId() throws Exception {
		mockMvc.perform(get("/api/v1/prices")
						.param("applicationDate", "2020-06-14T10:00:00")
						.param("brandId", "1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.message").value("Falta el parámetro obligatorio: productId"));
	}
}
